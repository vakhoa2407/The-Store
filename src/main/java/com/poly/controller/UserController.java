package com.poly.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.dao.UserDAO;
import com.poly.model.User;

@Controller
public class UserController {

	@Autowired
	UserDAO uDao;

	@GetMapping("/")
	public String showHome(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("users", uDao.findAll());
		return "user";
	}

	@RequestMapping("/admin/edit")
	public String edit(Model model, @RequestParam("id") String id) {
		model.addAttribute("user", uDao.getOne(id));
		model.addAttribute("users", uDao.findAll());
		return "user";
	}

	@RequestMapping("/admin/remove")
	public String remove(Model model, @RequestParam("id") String id) {
		User u = uDao.getOne(id);
		model.addAttribute("user", u);
		uDao.deleteById(id);
		model.addAttribute("users", uDao.findAll());
		model.addAttribute("message", "Xóa thành công!");
		return "user";
	}

	@RequestMapping("/admin/save")
	public String save(Model model, @RequestParam("id") String id, @RequestParam("password") String password,
			@RequestParam("fullname") String fullname, @RequestParam("email") String email,
			@RequestParam("admin") boolean admin) {
		Optional<User> uOpt = uDao.findById(id);
		User nu;
		if (!uOpt.isPresent()) {
			nu = new User();
			nu.setId(id);
			nu.setPassword(password);
			nu.setFullname(fullname);
			nu.setEmail(email);
			nu.setAdmin(admin);
			uDao.save(nu);
			model.addAttribute("message", "Thêm mới thành công!");
		} else {
			nu = uOpt.get();
			nu.setPassword(password);
			nu.setFullname(fullname);
			nu.setEmail(email);
			nu.setAdmin(admin);
			model.addAttribute("message", "Cập nhật thành công!");
			uDao.save(nu);
		}

		model.addAttribute("user", uDao.getOne(id));
		model.addAttribute("users", uDao.findAll());
		return "user";
	}

	@PostMapping("/admin/search")
	public String search(Model model, @RequestParam("keyword") Optional<String> keyword) {
		model.addAttribute("users",
				uDao.findByIdOrName("%" + keyword.orElse("%") + "%", "%" + keyword.orElse("%") + "%"));
		return "user";
	}
}
