package org.hbs.sender.model;

import org.hbs.core.util.EnumInterface;

public interface MessagePropertyEnum
{
	public enum EMessageType implements EnumInterface
	{
		Email, SMS;
	}

	public enum EMessage implements EnumInterface
	{
		Abort, Failed, InComplete, Pending, Processing, Ready, Retry, Send;
	}

	public enum ESMS implements EnumInterface
	{
		Accept_Language("Accept-Language"), Message("message"), Post("POST"), Sender("sender"), User_Agent("User-Agent"), WebsiteURL("WebsiteURL"), UserName("username"), Password(
				"password"), ReceiptantMobile("number"), Token("token"), Credit("credit");

		private final String eSMS;

		private ESMS(String eSMS)
		{
			this.eSMS = eSMS;
		}

		public String getValue()
		{
			return eSMS;
		}
	}
}
