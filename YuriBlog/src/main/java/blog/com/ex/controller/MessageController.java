package blog.com.ex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import blog.com.ex.model.entity.AdminEntity;
import blog.com.ex.service.MessageService;
import jakarta.servlet.http.HttpSession;

@Controller
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	@Autowired
	private HttpSession session;

	//blogメッセージ画面表示/massage")
	@GetMapping("/message")
	public String getMessage(Model model) {
		AdminEntity admin = (AdminEntity) session.getAttribute("admin");
		if(admin == null) {
			return "redirect:/login";
		}else {
			model.addAttribute("username",admin.getUsername());
			return "message.html";
		}
		
	}

}
