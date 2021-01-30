package com.marketmito.apporder.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.marketmito.apporder.service.UserService;







@Controller
public class LoginController {
	
	 @Autowired
	 private UserService userService;
	
	 @GetMapping(value = { "/login", "/"})
	public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model, 
			Principal principal,
			RedirectAttributes flash) {
		
		if (principal != null) {

			return "redirect:/dashboards/list";
		}
		
		if (error != null) {

			model.addAttribute("error","Error en en Login: Nombre de usuario o contraseña incorrecta, por favor vuelta a intentarlo");
			
		}
		
		if (logout != null) {

			model.addAttribute("success","Ha Cerrado sesión con éxito!.");
		}
		
		return "login";
	}
	


	

}
