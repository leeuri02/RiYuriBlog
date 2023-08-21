package blog.com.ex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.com.ex.model.dao.MessageDao;

@Service
public class MessageService {
	
	@Autowired
	private MessageDao messageDao;

}
