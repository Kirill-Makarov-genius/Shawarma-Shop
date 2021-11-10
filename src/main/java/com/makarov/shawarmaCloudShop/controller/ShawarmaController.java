package com.makarov.shawarmaCloudShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.makarov.shawarmaCloudShop.repository.UserRepository;

@Controller
@RequestMapping("/shawarma")
public class ShawarmaController {
	
	private UserRepository userRepository;
	
	@Autowired
	public ShawarmaController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@GetMapping("/create")
	public String formCreateShawarma(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "createShawarma";
	}
	
	
}
