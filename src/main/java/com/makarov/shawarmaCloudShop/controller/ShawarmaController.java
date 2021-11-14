package com.makarov.shawarmaCloudShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.makarov.shawarmaCloudShop.domain.Shawarma;
import com.makarov.shawarmaCloudShop.repository.IngredientRepository;
import com.makarov.shawarmaCloudShop.repository.ShawarmaRepository;

@Controller
@RequestMapping("/shawarma")
public class ShawarmaController {
	
	private ShawarmaRepository shawarmaRepository;
	private IngredientRepository ingredientRepository;
	
	@Autowired
	public ShawarmaController(ShawarmaRepository shawarmaRepository, 
					IngredientRepository ingredientRepository) {
		
		this.shawarmaRepository = shawarmaRepository;
		this.ingredientRepository = ingredientRepository;
	}
	
	@GetMapping("/create")
	public String formCreateShawarma(Model model) {
		model.addAttribute("shawarma", new Shawarma());
		model.addAttribute("ingredients", ingredientRepository.findAll());
		return "formCreateShawarma";
	}
	
	@PostMapping("/create")
	public String createShawarma(@ModelAttribute("shawarma") Shawarma shawarma) {
		
		shawarmaRepository.save(shawarma);
		
		return "redirect:/shawarma/create";
	}
	
}













