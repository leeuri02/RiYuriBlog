package blog.com.ex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.com.ex.model.entity.AdminEntity;
import blog.com.ex.service.AdminService;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	@Autowired
	private AdminService adminService;

	@Autowired
	HttpSession session;

	// loginページ表示
	@GetMapping("/login")
	public String getLoginPage() {
		return "login.html";
	}

	// login処理
	@PostMapping("/login/process")
	public String login(@RequestParam String username, @RequestParam String password) {
		AdminEntity admin = adminService.checkLogin(username, password);
		if (admin == null) {
			return "redirect:/login";
		} else {
			session.setAttribute("admin", admin);
			return "redirect:/home";
		}
	}

	// logout処理
	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/login";
	}

}
