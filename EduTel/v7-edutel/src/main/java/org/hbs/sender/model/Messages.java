package org.hbs.sender.model;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.hbs.core.util.CommonValidator;
import org.hbs.sender.model.MessagePropertyEnum.EMessageType;

@Entity
@Table(name = "messages")
public class Messages implements Serializable
{
	private static final long	serialVersionUID	= 5656802063996334220L;
	protected Timestamp			deliveryDate;
	protected String			message;
	protected String			messageId;
	protected String			messageName;
	protected String			messageSubject		= "System Message";
	protected String			messageType			= EMessageType.SMS.name();
	protected boolean			status				= true;

	public Messages()
	{
		super();
	}

	@Transient
	public String generateVTLMessage(Map<String, Object> dataMap) throws IOException
	{
		if (CommonValidator.isNotNullNotEmpty(dataMap))
		{
			Velocity.init();
			VelocityContext context = new VelocityContext();
			for (String key : dataMap.keySet())
				context.put(key, String.valueOf(dataMap.get(key)));
			StringWriter writer = new StringWriter();
			Velocity.evaluate(context, writer, "Message" + messageId, message);
			String generateMessage = writer.toString();
			writer.close();
			return generateMessage;
		}
		return "Not Able To Generate Email Content";

	}

	@Transient
	public String generateVTLSubject(Map<String, Object> dataMap) throws IOException
	{
		if (CommonValidator.isNotNullNotEmpty(dataMap))
		{
			Velocity.init();
			VelocityContext context = new VelocityContext();
			for (String key : dataMap.keySet())
				context.put(key, String.valueOf(dataMap.get(key)));
			StringWriter writer = new StringWriter();
			Velocity.evaluate(context, writer, "Subject" + messageId, messageSubject);
			String generateMessage = writer.toString();
			writer.close();
			return generateMessage;
		}
		return "Not Able To Generate Subject";

	}

	@Column(name = "deliveryDate")
	public Timestamp getDeliveryDate()
	{
		return deliveryDate;
	}

	@Column(name = "message")
	public String getMessage()
	{
		return message;
	}

	@Id
	@Column(name = "messageId")
	public String getMessageId()
	{
		return messageId;
	}

	@Column(name = "messageName")
	public String getMessageName()
	{
		return messageName;
	}

	@Column(name = "messageSubject")
	public String getMessageSubject()
	{
		return messageSubject;
	}

	@Column(name = "messageType")
	public String getMessageType()
	{
		return messageType;
	}

	@Column(name = "status")
	public boolean isStatus()
	{
		return status;
	}

	public void setDeliveryDate(Timestamp deliveryDate)
	{
		this.deliveryDate = deliveryDate;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public void setMessageId(String messageId)
	{
		this.messageId = messageId;
	}

	public void setMessageName(String messageName)
	{
		this.messageName = messageName;
	}

	public void setMessageSubject(String messageSubject)
	{
		this.messageSubject = messageSubject;
	}

	public void setMessageType(String messageType)
	{
		this.messageType = messageType;
	}

	public void setStatus(boolean status)
	{
		this.status = status;
	}

}
