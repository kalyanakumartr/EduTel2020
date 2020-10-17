package org.hbs.sender.bo;

import java.io.Serializable;
import java.util.List;

import org.hbs.sender.model.MessagesUserMapping;

public interface MessagesBo extends Serializable
{

	List<MessagesUserMapping> getEmailUserList();

	List<MessagesUserMapping> getSMSUserList();

	void updateMessageUserMapping(MessagesUserMapping _MUM);

}
