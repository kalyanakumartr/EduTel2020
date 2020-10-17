package org.hbs.sender.bo;

import java.util.List;

import org.hbs.sender.dao.MessagesUserMappingDAO;
import org.hbs.sender.model.MessagePropertyEnum.EMessageType;
import org.hbs.sender.model.MessagesUserMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessagesBoImpl implements MessagesBo
{
	private static final long serialVersionUID = 8672673703087069519L;
	
	@Autowired
	protected MessagesUserMappingDAO mumDAO;

	@Override
	public List<MessagesUserMapping> getEmailUserList()
	{
		return mumDAO.getUserMessageList(EMessageType.Email.name());
	}

	@Override
	public List<MessagesUserMapping> getSMSUserList()
	{
		return mumDAO.getUserMessageList(EMessageType.SMS.name());
	}

	@Override
	public void updateMessageUserMapping(MessagesUserMapping _MUM)
	{
		mumDAO.setUserMessageStatus(_MUM.getRetryCount(), _MUM.getMessageStatus(), _MUM.getAutoId());
	}

}
