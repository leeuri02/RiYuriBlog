package blog.com.ex.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="message")
public class MessageEntity {
	@Id
	@Column(name="message_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long messageId;
	
	@Column(name="author")
	private String author;
	
	@Column(name="date")
	private String date;
	
	@Column(name="message")
	private String message;
	
	public MessageEntity() {
	}

	public MessageEntity(String author, String date, String message) {
		this.author = author;
		this.date = date;
		this.message = message;
	}

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}
