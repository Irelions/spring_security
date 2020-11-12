package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
//@RequestMapping("/")
public class UserController {

	@Autowired
	private UserService userService;

//	@GetMapping("/")
//	public String showAllUsers(Model model){
//		List<User> allUsers = userService.listUsers();
//		model.addAttribute("allUsers", allUsers);
//		return "index";
//	}
//	@GetMapping("/show")
//	public String show(@RequestParam("id") int id, Model model) {
//		model.addAttribute("user", userService.showUser(id));
//		return "show";
//	}
//	@GetMapping("/new")
//	public String newUser(Model model) {
//		model.addAttribute("user", new User());
//		return "new";
//	}
//
//	@PostMapping
//	public String create(@ModelAttribute("user") User user) {
//		userService.add(user);
//		return "redirect:/";
//	}
//
//	@DeleteMapping("/delete/{id}")
//	public String delete(@PathVariable("id") int id) {
//		userService.delete(id);
//		return "redirect:/";
//	}
//
//	@PatchMapping("/edit/{id}")
//	public String update(@PathVariable("id") int id, Model model) {
//		User user = userService.showUser(id);
//		model.addAttribute("user", user);
//		return "new";
//	}

//	Методы были в задании

	@GetMapping(value = "hello")
//	@RequestMapping(value = "hello", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		List<String> messages = new ArrayList<>();
		messages.add("Hello!");
		messages.add("I'm Spring MVC-SECURITY application");
		messages.add("5.2.0 version by sep'19 ");
		model.addAttribute("messages", messages);
		return "hello";
	}

    @GetMapping(value = "login")
//    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }
}