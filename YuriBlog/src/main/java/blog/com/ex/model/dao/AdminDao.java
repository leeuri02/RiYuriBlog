package blog.com.ex.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import blog.com.ex.model.entity.AdminEntity;

public interface AdminDao extends JpaRepository<AdminEntity, Long> {
	//保存
	AdminEntity save(AdminEntity adminEntity);
	AdminEntity findByUsername(String username);
	AdminEntity findByUsernameAndPassword(String username,String password);

}
