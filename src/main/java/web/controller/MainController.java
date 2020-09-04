package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
public class MainController {
    private UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @GetMapping("/add")
    public String addUser() {
        return "addUser";
    }

    @PostMapping("/add")
    public String addPost(@RequestParam String name, @RequestParam String password, ModelMap model) {
        userService.addUser(new User(name, password));
        User user = userService.getUserByName(name);
        model.addAttribute("user", user);
        return "user";
    }
}
