package blog.com.ex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.com.ex.service.AdminService;

@Controller
public class RegisterController {

	@Autowired
	private AdminService adminService;

	// ユーザー作成ページ表示
	@GetMapping("/register")
	public String getRegisterPage() {
		return "register.html";
	}

	// ユーザー作成処理
	@PostMapping("/register/process")
	public String register(@RequestParam String username, @RequestParam String password, @RequestParam String email) {
		if (adminService.createAdmin(username, password, email)) {
			return "login.html";
		} else {
			return "register.html";
		}
	}

}
