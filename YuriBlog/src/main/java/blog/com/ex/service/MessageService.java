package blog.com.ex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.com.ex.model.dao.MessageDao;
import blog.com.ex.model.entity.MessageEntity;

@Service
public class MessageService {
	
	@Autowired
	private MessageDao messageDao;
	
	public boolean createMessage(String author,String date,String message) {
		MessageEntity messageList = messageDao.findByAuthor(author);
		if(messageList == null) {
			messageDao.save(new MessageEntity(author,date,message));
			return true;
		}else {
			return false;
		}
	}
	
	public List<MessageEntity> selectAll(){
			return messageDao.findAll();
		
	}
	
	
}

