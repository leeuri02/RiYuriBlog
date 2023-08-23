package blog.com.ex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.com.ex.model.entity.AdminEntity;
import blog.com.ex.model.entity.MessageEntity;
import blog.com.ex.service.MessageService;
import jakarta.servlet.http.HttpSession;

@Controller
public class MessageController {

	@Autowired
	private MessageService messageService;
	@Autowired
	private HttpSession session;

	// メッセージ画面表示
	@GetMapping("/message")
	public String getMessage(Model model) {
		AdminEntity admin = (AdminEntity) session.getAttribute("admin");
		if (admin == null) {
			return "redirect:/login";
		} else {
			model.addAttribute("username", admin.getUsername());
			List<MessageEntity> messageList = messageService.selectAll();
			model.addAttribute("messageList", messageList);
			return "message.html";
		}
	}

	// メッセージ作成処理
	@PostMapping("/message/register/process")
	public String getMessageRegisterProcess(@RequestParam String author, @RequestParam String date,
			@RequestParam String message, Model model) {
		if (messageService.createMessage(author, date, message)) {
			return "redirect:/message";
		} else {
			return "redirect:/message";
		}

	}
}
