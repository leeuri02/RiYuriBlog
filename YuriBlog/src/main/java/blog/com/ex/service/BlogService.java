package blog.com.ex.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.com.ex.model.dao.BlogDao;
import blog.com.ex.model.entity.BlogEntity;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;

	public List<BlogEntity> selectAll(Long adminId) {
		if (adminId == null) {
			return null;
		} else {
			return blogDao.findAll();
		}
	}

	// ブログの作成処理
	public boolean createBlog(String title, String date, String image, String contents) {
		BlogEntity blogList = blogDao.findByTitle(title);
		if (blogList == null) {
			blogDao.save(new BlogEntity(title, date, image, contents));
			return true;
		} else {
			return false;
		}
	}

	public BlogEntity getBlogPost(Long blogId) {
		if (blogId == null) {
			return null;
		} else {
			return blogDao.findByBlogId(blogId);
		}
	}

	// 更新処理
	public boolean editBlog(Long blogId, String title, String date, String image, String contents) {
		BlogEntity blogList = blogDao.findByBlogId(blogId);
		if (blogList == null) {
			return false;
		} else {
			blogList.setTitle(title);
			blogList.setDate(date);
			blogList.setImage(image);
			blogList.setContents(contents);
			blogDao.save(blogList);
			return true;
		}
	}

	// 削除処理
	public boolean deleteBlog(Long blogId) {
		if (blogId == null) {
			return false;
		} else {
			blogDao.deleteById(blogId);
			return true;
		}
	}

}
