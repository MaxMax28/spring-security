package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin/")
public class AdminController {

    private UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/allUsers")
    public String users(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "allUsers";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, Model model) {
        userService.deleteUser(id);
        List<User> userList = userService.getAllUsers();
        model.addAttribute("users", userList);
        return "redirect:/allUsers";
    }

    @GetMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "updateUser";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/alUsers";
    }
}
