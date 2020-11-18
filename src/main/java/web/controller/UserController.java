package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
//@RequestMapping("/")
public class UserController {

	@Autowired
	private UserService userService;


	@GetMapping("/show")
	public String show(@RequestParam("id") int id, Model model) {
		model.addAttribute("user", userService.showUser(id));
		return "show";
	}


	//Были изначально

	@GetMapping(value = "user")
//	@RequestMapping(value = "user", method = RequestMethod.GET)
	public String printWelcome(ModelMap model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User user_authentication) {
		List<User> user = userService.findUserByUsername(user_authentication.getUsername());
		model.addAttribute("user_authentication", user_authentication.getUsername());
		model.addAttribute("findUserByUsername", user);
		return "user";
	}

    @GetMapping(value = "login")
//    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
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