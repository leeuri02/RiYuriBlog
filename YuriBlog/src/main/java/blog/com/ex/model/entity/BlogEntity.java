package blog.com.ex.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="blogsite")
public class BlogEntity {
	@Id
	@Column(name="blog_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long blogId;
	
	@Column(name="title")
	private String title;
	
	@Column(name="date")
	private String date;
	
	@Column(name="image")
	private String image;
	
	@Column(name="contents")
	private String contents;

	public BlogEntity() {
		
	}
	
	public BlogEntity(String title, String date, String image, String contents) {
		this.title = title;
		this.date = date;
		this.image = image;
		this.contents = contents;
	}

	public BlogEntity(Long blogId, String title, String date, String image, String contents) {
		this.blogId = blogId;
		this.title = title;
		this.date = date;
		this.image = image;
		this.contents = contents;
	}

	public Long getBlogId() {
		return blogId;
	}

	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	@Override
	public String toString() {
		return "BlogEntity [blogId=" + blogId + ", title=" + title + ", date=" + date + ", image=" + image
				+ ", contents=" + contents + "]";
	}
	
	


}
