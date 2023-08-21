package blog.com.ex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.com.ex.model.dao.AdminDao;
import blog.com.ex.model.entity.AdminEntity;

@Service
public class AdminService {
	
	@Autowired
	private AdminDao adminDao;
	

	public boolean createAdmin(String username,String password,String email) {
		if(adminDao.findByUsername(username) == null) {
			adminDao.save(new AdminEntity(username,password,email));
			return true;
		}else {
			return false;
		}
	}
	
	public AdminEntity checkLogin(String username,String password) {
		AdminEntity adminEntity = adminDao.findByUsernameAndPassword(username,password);
		if(adminEntity == null) {
			return null;
		}else {
			return adminEntity;
		}
	}
	
}
