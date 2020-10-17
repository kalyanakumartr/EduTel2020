package org.hbs.sender.dao;

import java.util.List;

import org.hbs.sender.model.MessagesUserMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MessagesUserMappingDAO extends JpaRepository<MessagesUserMapping, Integer>
{
	@Transactional
	@Modifying
	@Query("Update MessagesUserMapping MUM Set MUM.retryCount = :retryCount, messageStatus = :messageStatus Where MUM.autoId = :autoId")
	void setUserMessageStatus(@Param("retryCount") int retryCount, @Param("messageStatus") String messageStatus, @Param("autoId") int autoId);

	@Query("select MUM FROM MessagesUserMapping MUM Where MUM.retryCount < 3 AND MUM.messages.deliveryDate < CURRENT_TIMESTAMP AND MUM.messages.status = true AND  MUM.messageStatus = 'Pending' AND MUM.messages.messageType = :messageType Order By MUM.retryCount Desc")
	public List<MessagesUserMapping> getUserMessageList(@Param("messageType") String messageType);
}