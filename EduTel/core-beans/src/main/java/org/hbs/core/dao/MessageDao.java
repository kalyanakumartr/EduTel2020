package org.hbs.core.dao;

import java.util.List;

import org.hbs.core.beans.model.V7Messages;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//@Repository
public interface MessageDao //extends JpaRepository<V7Messages, String>
{

	@Query("From V7Messages where producer.producerId = :producerId and messageId = :messageId and status = true")
	List<V7Messages> getByMessageId(@Param("producerId") String producerId, @Param("messageId") String messageId);

	@Query("From V7Messages where producer.producerId = :producerId and messageType = :messageType and status = true")
	List<V7Messages> getByMessageType(@Param("producerId") String producerId, @Param("messageType") String messageType);

}
