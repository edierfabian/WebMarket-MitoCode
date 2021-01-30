package com.marketmito.apporder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.marketmito.apporder.UserRegister.UsersRegister;
import com.marketmito.apporder.entity.Customer;
import com.marketmito.apporder.entity.Product;
import com.marketmito.apporder.entity.Users;
import com.marketmito.apporder.pagination.PageRender;
import com.marketmito.apporder.repository.UserRepo;
import com.marketmito.apporder.service.UserService;



@Controller
@RequestMapping("/register")
@SessionAttributes("user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepo userRepo;
	
	@GetMapping
	public String showRegisterForm(Model model) {
		return "register";
		
	}
	@ModelAttribute("user")
    public  UsersRegister usersRegister() {
        return new UsersRegister();
    }
	
		@PostMapping
	public String registerUser(@ModelAttribute("user")UsersRegister usersRegister, Model model) {
	
		if (userRepo.findByUserName(usersRegister.getUserName())!=null) {
			
			return "redirect:/register?warning";
			
		}	
			
		userService.save(usersRegister);
		
		return "redirect:/register?success";
		
		
	}
	
		
		

}
