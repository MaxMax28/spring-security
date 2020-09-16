package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.repository.RoleDao;
import web.model.Role;
import web.model.User;
import web.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    private RoleDao roleDao;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(UserService userService, RoleDao roleDao, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
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
        return "redirect:/admin/allUsers";
    }

    @GetMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "updateUser";
    }

    @PostMapping("/update")
    public String update(@RequestParam Long id, @RequestParam String name,
                         @RequestParam(required = false) String password,
                         @RequestParam String roles,
                         ModelMap model) {

        User oldUser = userService.getUserById(id);
        oldUser.setName(name);

        if (password != null && !password.equals("")) {
            oldUser.setPassword(passwordEncoder.encode(password));
        }

//        if (roles.size() > 1) {
//            oldUser.setRoles(roles);
//        } else {
//            Set<Role> roles1 = new HashSet<>();
//            for (Role role : roles) {
//                if (role.getName().equals("ADMIN")) {
//                    roles1.add(roleDao.getRoleByName("ROLE_ADMIN"));
//                } else {
//                    roles1.add(roleDao.getRoleByName("ROLE_USER"));
//                }
//                oldUser.setRoles(roles1);
//            }
//        }



        Set<Role> roles1 = new HashSet<>();
        if (roles.equals("ADMIN")) {
            roles1.add(roleDao.getRoleById(1L));
            //переделать по имени
            //roles1.add(roleDao.getRoleByName(roles));
        } else if (roles.equals("USER")) {
            roles1.add(roleDao.getRoleById(2L));
        }
        oldUser.setRoles(roles1);

        userService.updateUser(oldUser);
        return "redirect:/admin/allUsers";
    }

    @GetMapping("/add")
    public String addUser() {
        return "addUser";
    }

    @PostMapping("/add")
    public String addPost(@RequestParam String name,
                          @RequestParam String password,
                          @RequestParam String roles,
                          ModelMap model) {

        User user = new User();
        user.setName(name);
        user.setPassword(password);

        Set<Role> roles1 = new HashSet<>();

        if (roles.equals("ADMIN")) {
            roles1.add(roleDao.getRoleById(1L));
        } else if (roles.equals("USER")) {
            roles1.add(roleDao.getRoleById(2L));
        }
        user.setRoles(roles1);
        userService.addUser(user);
        model.addAttribute("user", user);
        return "redirect:/admin/allUsers";
    }
}
