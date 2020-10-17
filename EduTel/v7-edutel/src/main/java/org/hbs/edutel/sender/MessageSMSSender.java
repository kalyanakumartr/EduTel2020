package org.hbs.edutel.sender;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.hbs.core.util.CommonValidator;
import org.hbs.sender.bo.MessagesBo;
import org.hbs.sender.model.MessagePropertyEnum.EMessage;
import org.hbs.sender.model.MessagePropertyEnum.ESMS;
import org.hbs.sender.model.MessagesUserMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageSMSSender implements Serializable
{
	private static final long	serialVersionUID	= 5378031148500056351L;
	private final String		USER_AGENT			= "Mozilla/5.0";

	@Value("${edutel.sms.website.url}")
	private String				websiteURL;

	@Value("${edutel.sms.sender.name}")
	private String				smsSenderName;

	@Value("${edutel.sms.token}")
	private String				smsToken;

	@Value("${edutel.sms.credit}")
	private String				smsCredit;

	@Autowired
	private MessagesBo			messageBo;

	public void sendMessageToUserByMedia() throws ClassNotFoundException, SQLException
	{

		List<MessagesUserMapping> messageUserList = messageBo.getSMSUserList();
		if (CommonValidator.isListFirstNotEmpty(messageUserList))
		{
			String smsMessage = "";
			String mobileNo = "";
			HttpPost httpPost = new HttpPost(websiteURL);
			httpPost.setHeader(ESMS.User_Agent.getValue(), USER_AGENT);
			
			CloseableHttpClient client = HttpClientBuilder.create().build();
			
			for (final MessagesUserMapping _MUM : messageUserList)
			{
				try
				{
					smsMessage = _MUM.constructedMessage();

					if (CommonValidator.isNotNullNotEmpty(smsMessage) && CommonValidator.isArrayFirstNotNull(_MUM.to))
					{
						mobileNo = _MUM.to[0];
						if (CommonValidator.isNotNullNotEmpty(mobileNo) && CommonValidator.isEqual(_MUM.getMessageStatus(), EMessage.Pending.name()))
						{
							long startTime = System.currentTimeMillis();

							List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
							urlParameters.add(new BasicNameValuePair(ESMS.Token.getValue(), smsToken));
							urlParameters.add(new BasicNameValuePair(ESMS.Credit.getValue(), smsCredit));
							urlParameters.add(new BasicNameValuePair(ESMS.Sender.getValue(), smsSenderName));
							urlParameters.add(new BasicNameValuePair(ESMS.ReceiptantMobile.getValue(), mobileNo));
							urlParameters.add(new BasicNameValuePair(ESMS.Message.getValue(), smsMessage));
							httpPost.setEntity(new UrlEncodedFormEntity(urlParameters));

							int statusCode = 0;
							String reasonPhrase = "";
							try
							{
								CloseableHttpResponse response = client.execute(httpPost);
								statusCode = response.getStatusLine().getStatusCode();
								if (statusCode == HttpURLConnection.HTTP_OK)
								{
									_MUM.setMessageStatus(EMessage.Send.name());
								}
								else
									throw new Exception(response.getStatusLine().getReasonPhrase());
							}
							catch (Exception excep)
							{
								statusCode = 0;
								StringWriter logMessageWriter = new StringWriter();
								excep.printStackTrace(new PrintWriter(logMessageWriter));
								reasonPhrase = logMessageWriter.toString();
								if (reasonPhrase.length() > 400)
								{
									reasonPhrase = reasonPhrase.substring(0, 400);
								}
								_MUM.setMessageStatus(reasonPhrase);

							}

							long differenceTime = System.currentTimeMillis() - startTime;

							new Thread(new Runnable() {
								@Override
								public void run()
								{
									try
									{
										if (CommonValidator.isNotEqual(_MUM.getMessageStatus(), EMessage.Send))
										{
											if (_MUM.getRetryCount() >= 3)
												_MUM.setMessageStatus(EMessage.Failed.name());
										}
										_MUM.setRetryCount(_MUM.getRetryCount() + 1);

										messageBo.updateMessageUserMapping(_MUM);

									}
									catch (Exception ioe)
									{

									}

								}
							}).start();

							// Control Message Trigger Speed of 60 Messages / Second
							if (differenceTime < 10)
							{
								Thread.sleep(10 - differenceTime);
							}

						} 
					}
				}
				catch (IOException | InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}

	}
}
