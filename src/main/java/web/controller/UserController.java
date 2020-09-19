package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import javax.servlet.http.PushBuilder;
import java.util.ArrayList;
import java.util.List;


@Controller
public class UserController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/user")
	public String user(Model model, Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		model.addAttribute("id", user.getId());
		model.addAttribute("name", user.getName());
		model.addAttribute("roles", user.getRoles());
		return "user";
	}
}