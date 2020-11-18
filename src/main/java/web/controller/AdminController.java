package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String showAllUsers(Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User user_authentication){
        List<User> allUsers = userService.listUsers();
        model.addAttribute("allUsers", allUsers);
        model.addAttribute("user_authentication", user_authentication.getUsername());
        return "admin";
    }
    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping
    public String create(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/admin/";
    }

    @PatchMapping("/edit/{id}")
//    @RequestMapping(value = "/edit/{id}", method = RequestMethod.PATCH)
    public String update(@PathVariable("id") int id, Model model) {
        User user = userService.showUser(id);
        model.addAttribute("user", user);
        return "new";
    }

    @DeleteMapping("/delete/{id}")
//    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin/";
    }

    @GetMapping(value="/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }
}
