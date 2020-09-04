package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
	public String user(@RequestParam String name, Model model) {
		User user = userService.getUserByName(name);
		model.addAttribute("user", user);
		return "user";
	}
}