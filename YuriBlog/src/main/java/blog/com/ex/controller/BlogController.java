package blog.com.ex.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import blog.com.ex.model.entity.AdminEntity;
import blog.com.ex.model.entity.BlogEntity;
import blog.com.ex.service.BlogService;
import jakarta.servlet.http.HttpSession;

@Controller
public class BlogController {

	@Autowired
	private BlogService blogService;
	@Autowired
	private HttpSession session;

	// ホームページ表示
	@GetMapping("/home")
	public String getHomePage(Model model) {
		AdminEntity admin = (AdminEntity) session.getAttribute("admin");
		if (admin == null) {
			return "redirect:/login";
		} else {
			model.addAttribute("username", admin.getUsername());
			return "home.html";
		}
	}

	// blogListページ表示
	@GetMapping("/blog/list")
	public String getBlogList(Model model) {
		AdminEntity admin = (AdminEntity) session.getAttribute("admin");
		if (admin == null) {
			return "redirect:/login";
		} else {
			List<BlogEntity> blogList = blogService.selectAll(admin.getAdminId());
			model.addAttribute("blogList", blogList);
			model.addAttribute("username", admin.getUsername());
			return "list.html";
		}
	}

	// blog作成画面表示
	@GetMapping("/blog/register")
	public String getBlogRegister(Model model) {
		AdminEntity admin = (AdminEntity) session.getAttribute("admin");
		if (admin == null) {
			return "redirect:/login";
		} else {
			model.addAttribute("username", admin.getUsername());
			return "blogregister.html";
		}
	}

	// blog作成処理
	@PostMapping("/blog/register/process")
	public String getBlogRegisterProcess(@RequestParam String title, @RequestParam String date,
			@RequestParam MultipartFile image, @RequestParam String contents, Model model) {
		AdminEntity admin = (AdminEntity) session.getAttribute("admin");
		if (admin == null) {
			return "redirect:/login";
		} else {
			String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date())
					+ image.getOriginalFilename();
			try {
				Files.copy(image.getInputStream(), Path.of("src/main/resources/static/image/" + fileName));
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (blogService.createBlog(title, date, fileName, contents)) {
				return "redirect:/blog/list";
			} else {
				return "redirect:/blog/register";
			}
		}
	}

	// blog編集・画面表示
	@GetMapping("/blog/edit/{blogId}")
	public String getBlogEdit(@PathVariable Long blogId, Model model) {
		AdminEntity admin = (AdminEntity) session.getAttribute("admin");
		if (admin == null) {
			return "redirect:/login";
		} else {
			BlogEntity blogList = blogService.getBlogPost(blogId);
			if (blogList == null) {
				return "redirect:/blog/list";
			} else {
				model.addAttribute("username", admin.getUsername());
				model.addAttribute("blogList", blogList);
				return "edit.html";
			}
		}
	}

	// 編集処理（更新）
	@PostMapping("/blog/edit/process")
	public String getBlogEditProcess(@RequestParam Long blogId, @RequestParam String title, @RequestParam String date,
			@RequestParam MultipartFile image, @RequestParam String contents, Model model) {
		AdminEntity admin = (AdminEntity) session.getAttribute("admin");
		if (admin == null) {
			return "redirect:/login";
		} else {
			String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date())
					+ image.getOriginalFilename();
			try {
				Files.copy(image.getInputStream(), Path.of("src/main/resources/static/image/" + fileName));
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (blogService.editBlog(blogId, title, date, fileName, contents)) {
				return "redirect:/blog/list";
			} else {
				return "redirect:/blog/edit" + blogId;
			}
		}
	}

	// 削除処理
	@PostMapping("/blog/delete")
	public String delete(@RequestParam Long blogId) {
		if (blogService.deleteBlog(blogId)) {
			return "redirect:/blog/list";
		} else {
			return "redirect:/blog/edit/" + blogId;
		}
	}
}
