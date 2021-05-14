package com.yauhe.online_cosmetics_store.controller;

import com.yauhe.online_cosmetics_store.entity.User;
import com.yauhe.online_cosmetics_store.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/registration")
public class RegistratoinController {

	@Autowired
	UserServiceImpl userService;

	@PostMapping
	public String registraionUser(Model model, @ModelAttribute User user, RedirectAttributes redirectAttributes) {
		boolean success = userService.userRegistration(user);
		String response = success ? "Успешно зарегистрирован" : "Ошибка регистрации. Попробуйте позже.";
		redirectAttributes.addFlashAttribute("success", response);
		return "redirect:/login";
	}

	@GetMapping
	String getRegistration() {
		return "login";
	}

}
