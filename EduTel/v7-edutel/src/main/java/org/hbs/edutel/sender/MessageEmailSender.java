package org.hbs.edutel.sender;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.hbs.core.beans.model.IMessages.EMessageStatus;
import org.hbs.core.util.CommonValidator;
import org.hbs.sender.bo.MessagesBo;
import org.hbs.sender.model.MessagePropertyEnum.EMessage;
import org.hbs.sender.model.MessagesUserMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MessageEmailSender implements Serializable
{
	private static final long	serialVersionUID	= 1301322022734860231L;

	@Value("${edutel.email.from.id}")
	private String				emailFromId;

	@Value("${edutel.email.from.name}")
	private String				emailFromName;

	@Value("${edutel.email.hostname:'smtp.gmail.com'}")
	private String				emailHostName;

	@Value("${edutel.email.port:'587'}")
	private String				emailPortNo;

	@Value("${edutel.email.username}")
	private String				emailUserName;

	@Value("${edutel.email.password}")
	private String				emailPassword;

	@Autowired
	private MessagesBo			messageBo;

	public void sendMessageToUserByMedia()
	{
		try
		{
			List<MessagesUserMapping> messageUserList = messageBo.getEmailUserList();
			if (CommonValidator.isListFirstNotEmpty(messageUserList))
			{
				JavaMailSender emailSender = createEmailSender();
				MimeMessage mimeMessage = emailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
				helper.setFrom(emailFromId, emailFromName);
				for (final MessagesUserMapping _MUM : messageUserList)
				{
					try
					{
						sendMimeMessage(_MUM, helper, emailSender);
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
					finally
					{
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
								catch (Exception excep)
								{
									excep.printStackTrace();
								}
							}
						}).start();
					}

				} 
			} 
			else
				System.out.println(">>>>>>>>>>>>>>>>>>>>> NO Messages Available <<<<<<<<<<<<<<<<<<<<<<");
		}
		catch (Exception excep)
		{
			excep.printStackTrace();
		}
	}

	private JavaMailSender createEmailSender()
	{
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(emailHostName);
		mailSender.setPort(Integer.parseInt(emailPortNo));
		mailSender.setUsername(emailUserName);
		mailSender.setPassword(emailPassword);
		Properties props = mailSender.getJavaMailProperties();

		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", true);

		return mailSender;
	}

	private void sendMimeMessage(MessagesUserMapping MUM, MimeMessageHelper helper, JavaMailSender emailSender) throws MessagingException, IOException, ClassNotFoundException, SQLException
	{
		String message = MUM.constructedMessage();
		if (CommonValidator.isNotNullNotEmpty(message))
		{
			helper.setSubject(MUM.constructedSubject()); // Do NOT move from here to, cc, bcc will populate here
			helper.setText(message, true);
			helper.setTo(MUM.to);
			if (MUM.cc != null)
				helper.setCc(MUM.cc);
			if (MUM.bcc != null)
				helper.setBcc(MUM.bcc);
			// Send Mail
			emailSender.send(helper.getMimeMessage());
			MUM.setMessageStatus(EMessageStatus.Send.name());
		}
	}

}
