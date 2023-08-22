package blog.com.ex.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import blog.com.ex.model.entity.MessageEntity;

public interface MessageDao extends JpaRepository<MessageEntity, Long> {
	
	MessageEntity save(MessageEntity messageEntity);
	List<MessageEntity>findAll();
	MessageEntity findByMessageId(Long MessageId);
	MessageEntity findByAuthor(String author);

}
