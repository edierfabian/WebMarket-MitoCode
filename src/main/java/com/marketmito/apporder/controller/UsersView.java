package com.marketmito.apporder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.marketmito.apporder.entity.Product;
import com.marketmito.apporder.entity.Users;
import com.marketmito.apporder.pagination.PageRender;
import com.marketmito.apporder.repository.UserRepo;
import com.marketmito.apporder.service.UserService;

@Controller
@RequestMapping("/usuarios")
@SessionAttributes("user")
public class UsersView {
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping(value = "/list")
	public String getAllUsers(@RequestParam(name="page",defaultValue = "0")int page,			
			Model model) throws Exception {
		List<Users> user=userService.getAll();
		model.addAttribute("user",user);
		return "user/user";
	
	}
	
	@GetMapping(value = "/delete/{id}")
	public String delete(@PathVariable Long id, Model model ) {
	
		
	userService.delete(id);	
		return "redirect:/usuarios/list";
		
	}
	
	
}	
