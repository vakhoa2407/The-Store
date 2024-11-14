package com.poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.dao.UserDAO;
import com.poly.model.User;
import com.poly.service.SessionService;

@Controller
public class LoginController {
	@Autowired
	UserDAO dao;

	@Autowired
	SessionService session;

	@RequestMapping("/login")
	public String login(Model model) {
		User u = session.get("login");
		if (u == null) {
			return "login";
		} else {
			model.addAttribute("user", new User());
			model.addAttribute("users", dao.findAll());
			return "user";
		}
	}

	@PostMapping("/login")
	public String login(Model model, @RequestParam("username") String username,
			@RequestParam("password") String password) {
		try {
			User user = dao.getOne(username);
			if (!user.getPassword().equals(password)) {
				model.addAttribute("message", "Password không đúng");
			} else {
				// Lưu thông tin user vào session
				session.set("login", user);

				// Điều hướng theo uri
				String uri = session.get("security-uri");
				if (uri != null && !uri.isEmpty()) {
					if (uri.contains("/admin/")) {
						return "redirect:/login";
					} else if (uri.contains("/account/")) {
						return "redirect:/login";
					}
				}
				model.addAttribute("user", new User());
				model.addAttribute("users", dao.findAll());
				return "user";
			}
			return "login";
		} catch (Exception e) {
			model.addAttribute("message", "Username không tồn tại");
			return "login";
		}
	}

	@RequestMapping("/logout")
	public String logout() {
		session.remove("login");
		session.remove("security-uri");
		return "redirect:/login";
	}
	
}
