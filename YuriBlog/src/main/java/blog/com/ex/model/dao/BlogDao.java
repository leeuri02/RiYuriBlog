package blog.com.ex.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import blog.com.ex.model.entity.BlogEntity;

public interface BlogDao extends JpaRepository<BlogEntity, Long> {

	BlogEntity save(BlogEntity blogEntity);
	List<BlogEntity>findAll();
	BlogEntity findByBlogId(Long blogId);
	BlogEntity findByTitle(String title);
}
