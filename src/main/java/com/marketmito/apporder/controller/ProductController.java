package com.marketmito.apporder.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.marketmito.apporder.entity.Product;
import com.marketmito.apporder.pagination.PageRender;
import com.marketmito.apporder.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Controller
@RequestMapping("/products")
@SessionAttributes("product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping(value="/loadproducts/{term}", produces = {"application/json"})
	public @ResponseBody List<Product> loadProducts(@PathVariable String term){
		List<Product> products=new ArrayList<>();
		try {
			products=productService.getProductByName(term);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return products;
	}
	
	@GetMapping(value="/list")
	public String getAllProducts(@RequestParam(name="page",defaultValue="0")int page,
			Model model ) {
		
		try {
			
			Pageable pageRequest=PageRequest.of(page, 4);
			//Page<Customer>customers=customerService.getAll(pageRequest);
			Page<Product>products=productService.getAll(pageRequest);
			PageRender<Product> pageRender=new PageRender<Product>("/products/list/", products);
			
			model.addAttribute("title","Listado de Clientes");
			model.addAttribute("products",products);
			model.addAttribute("page",pageRender);
		}catch (Exception e) {
			// TODO: handle exception
		}
		//return "customer/customer";
		return "product/product";
	}
	
	@GetMapping(value="/new")
	public String newProduct(Model model) {
		
		Product product=new Product();
		model.addAttribute("title", "Registrar");
		model.addAttribute("product", product);
		
		return "product/form";
		
	}
	
	@PostMapping(value="/save")
	public String saveProduct(@Valid Product product, BindingResult result, 
			RedirectAttributes flash,SessionStatus status,Model model) {
		
		try {
			if(result.hasErrors()) {
				model.addAttribute("title","Registrar Producto");
				return "product/form";
			
			} 
			
		
			String messageFlash=(product.getId()!=null)?"Producto editado Correctamente!":
				"Producto editado Correctamente!";
			
			productService.saveOrUpdate(product);
			status.setComplete();
			flash.addFlashAttribute("success",messageFlash);
		} catch (Exception e) {
				// TODO: handle exception
		}
		return "redirect:/products/list";
			
	} 
	
	@GetMapping(value = "/edit/{id}")
	public String editProduct(@PathVariable(value="id") Long id,Model model,
			RedirectAttributes flash) {
		
		Optional<Product> product;
		try {
			if(id>0) {
				product=productService.getOne(id);
				if(!product.isPresent()) {
					flash.addAttribute("error","el codigo del product no existe");
					return "redirect:/products/list";
				}
			}else {
				flash.addAttribute("error","el codigo del producto no existe");
				return "redirect:/products/list";
			}
			model.addAttribute("product",product);
			model.addAttribute("title","Editar producto");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "product/form";
		
	}
	
	@GetMapping(value = "/delete/{id}")
	public String delete(@PathVariable Long id, Model model ) throws Exception {
	
		
		productService.deleteById(id);;	
		return "product/form";
		
	}
	
	
	
}
