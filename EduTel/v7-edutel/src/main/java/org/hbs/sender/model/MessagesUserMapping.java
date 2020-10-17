package org.hbs.sender.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hbs.core.util.CommonValidator;

import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
@Table(name = "messagesusermapping")
public class MessagesUserMapping implements MessagePropertyEnum, Serializable
{
	public static int			MAX_RETRY_COUNT		= 3;
	private static final long	serialVersionUID	= -6343302067563654712L;
	protected Integer			autoId;
	protected String			externalMessage;
	protected Messages			messages;
	protected String			messageStatus;
	protected Integer			retryCount			= 0;
	public String[]				to;
	public String[]				cc;
	public String[]				bcc;
	protected Blob				dataObject;

	public MessagesUserMapping()
	{
		super();
	}

	@Id
	@Column(name = "autoId")
	public Integer getAutoId()
	{
		return autoId;
	}

	@SuppressWarnings("unchecked")
	public String constructedMessage() throws IOException, ClassNotFoundException, SQLException
	{
		Map<String, Object> dataMap = null;
		if (CommonValidator.isNotNullNotEmpty(externalMessage) && externalMessage.trim().startsWith("{"))
		{
			dataMap = new ObjectMapper().readValue(externalMessage, LinkedHashMap.class);
		}
		else if (CommonValidator.isNotNullNotEmpty(dataObject))
		{
			dataMap = (Map<String, Object>) deserialize(dataObject);
		}

		if (dataMap != null && messages.messageType.equals(EMessageType.Email.name()))
		{
			Object object = dataMap.get("to");
			if (object == null)
			{
				object = dataMap.get("Users_usEmail");
			}
			
			if (object != null)
			{
				if (object instanceof String)
					to = new String[] { (String) object };
				else if (object instanceof String[])
					to = (String[]) object;
				else if (object instanceof ArrayList)
				{
					ArrayList<String> list = (ArrayList<String>) object;
					to = list.toArray(new String[list.size()]);
				}
			}

			object = dataMap.get("cc");

			if (object != null)
			{
				if (object instanceof String)
					cc = new String[] { (String) object };
				else if (object instanceof String[])
					cc = (String[]) object;
				else if (object instanceof ArrayList)
				{
					ArrayList<String> list = (ArrayList<String>) object;
					cc = list.toArray(new String[list.size()]);
				}
			}

			object = dataMap.get("bcc");

			if (object != null)
			{
				if (object instanceof String)
					bcc = new String[] { (String) object };
				else if (object instanceof String[])
					bcc = (String[]) object;
				else if (object instanceof ArrayList)
				{
					ArrayList<String> list = (ArrayList<String>) object;
					bcc = list.toArray(new String[list.size()]);
				}
			}
		}
		if (dataMap != null && messages.messageType.equals(EMessageType.SMS.name()))
		{
			Object object = dataMap.get("to");
			if (object == null)
			{
				object = dataMap.get("Users_usMobileNo");
			}
			
			if (object != null)
			{
				if (object instanceof String)
					to = new String[] { (String) object };
				else if (object instanceof String[])
					to = (String[]) object;
				else if (object instanceof ArrayList)
				{
					ArrayList<String> list = (ArrayList<String>) object;
					to = list.toArray(new String[list.size()]);
				}
			}
		}
		return this.messages.generateVTLMessage(dataMap);
	}

	@SuppressWarnings("unchecked")
	public String constructedSubject() throws IOException, ClassNotFoundException, SQLException
	{
		if (CommonValidator.isNotNullNotEmpty(externalMessage) && externalMessage.trim().startsWith("{"))
			return this.messages.generateVTLSubject(new ObjectMapper().readValue(externalMessage, LinkedHashMap.class));
		else if (CommonValidator.isNotNullNotEmpty(dataObject))
		{
			Map<String, Object> dataMap = (Map<String, Object>) deserialize(dataObject);
			return this.messages.generateVTLSubject(dataMap);
		}
		return "";
	}

	@Column(name = "externalMessage")
	public String getExternalMessage()
	{
		return externalMessage;
	}

	@ManyToOne(targetEntity = Messages.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "messageId")
	public Messages getMessages()
	{
		return messages;
	}

	@Column(name = "messageStatus")
	public String getMessageStatus()
	{
		return messageStatus;
	}

	@Column(name = "retryCount")
	public Integer getRetryCount()
	{
		return retryCount;
	}

	public void setAutoId(Integer autoId)
	{
		this.autoId = autoId;
	}

	public void setExternalMessage(String externalMessage)
	{
		this.externalMessage = externalMessage;
	}

	public void setMessages(Messages messages)
	{
		this.messages = messages;
	}

	public void setMessageStatus(String messageStatus)
	{
		this.messageStatus = messageStatus;
	}

	public void setRetryCount(Integer retryCount)
	{
		this.retryCount = retryCount;
	}

	@Column(name = "dataObject")
	public Blob getDataObject()
	{
		return dataObject;
	}

	public void setDataObject(Blob dataObject)
	{
		this.dataObject = dataObject;
	}

	public Object deserialize(Blob blob) throws IOException, ClassNotFoundException, SQLException
	{
		ObjectInputStream ois = new ObjectInputStream(blob.getBinaryStream());
		return ois.readObject();
	}
}