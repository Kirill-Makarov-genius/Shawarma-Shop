package com.makarov.shawarmaCloudShop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.makarov.shawarmaCloudShop.domain.Ingredient;
import com.makarov.shawarmaCloudShop.domain.Ingredient.Type;
import com.makarov.shawarmaCloudShop.domain.Shawarma;
import com.makarov.shawarmaCloudShop.repository.IngredientRepository;
import com.makarov.shawarmaCloudShop.repository.ShawarmaRepository;

@Controller
@RequestMapping("/shawarma")
@SessionAttributes("cart")
public class ShawarmaController {
	
	private ShawarmaRepository shawarmaRepository;
	private IngredientRepository ingredientRepository;
	
	@Autowired
	public ShawarmaController(ShawarmaRepository shawarmaRepository, 
					IngredientRepository ingredientRepository) {
		
		this.shawarmaRepository = shawarmaRepository;
		this.ingredientRepository = ingredientRepository;
	}
	
	@ModelAttribute("cart")
	public List<Shawarma> createCart(){
		return new ArrayList<>();
	}
	
	
	
	@GetMapping("/all")
	public String showAll(Model model) {
		Iterable<Shawarma> shawarmas = shawarmaRepository.findAll();
		model.addAttribute("shawarmas", shawarmas);
		return "shawarma/showAllShawarmas";
	}
	
	@GetMapping("/cart")
	public String cart(@ModelAttribute("cart") List<Shawarma> cart) {
		return "shawarma/cart";
	}
	
	
	
	@GetMapping("/create")
	public String formCreateShawarma(Model model) {
		model.addAttribute("shawarma", new Shawarma());
		groupByType(model);
		return "shawarma/formCreateShawarma";
	}
	
	@PostMapping("/create/")
	public String createShawarma(
			@Valid Shawarma shawarma,
			Errors errors, Model model,
			@ModelAttribute("cart") List<Shawarma> cart) {
		if (errors.hasErrors()) {
			groupByType(model);
			return "shawarma/formCreateShawarma";
		}

		cart.add(shawarma);
		shawarmaRepository.save(shawarma);
		
		return "redirect:/shawarma/cart";
	}
	
	
	@GetMapping("/info/{id}")
	public String showShawarma(@PathVariable("id") Long id, Model model) {
		model.addAttribute("shawarma", shawarmaRepository.findById(id).get());
		return "shawarma/showShawarma";
		
	}
	
	
	@GetMapping("/edit/{id}")
	public String formEditShawarma(@PathVariable("id") Long id, Model model) {
		Optional<Shawarma> shawarma = shawarmaRepository.findById(id);
		model.addAttribute("shawarma", shawarma.get());
		groupByType(model);
		return "shawarma/formEditShawarma";
	}
	
	@PatchMapping("/edit/{id}")
	public String editShawarma(@PathVariable("id") Long id,
			@Valid Shawarma newShawarma,
			Errors errors, Model model) {
		if (errors.hasErrors()) {
			groupByType(model);
			return "shawarma/formEditShawarma";
		}
		Shawarma editShawarma = shawarmaRepository.findById(id).get();
		editShawarma.setName(newShawarma.getName());
		editShawarma.setIngredients(newShawarma.getIngredients());
		shawarmaRepository.save(editShawarma);
		return "redirect:/shawarma/info/" + id;
	}
	
	@DeleteMapping("delete/{id}")
	public String deleteShawarma(@PathVariable("id") Long id) {
		shawarmaRepository.deleteById(id);
		return "redirect:/shawarma/all";
	}
	
	
	
	
	
	
	
	 
	private void groupByType(Model model){
		Type[] types = Ingredient.Type.values();
		List<Ingredient> ingredients;
		for (Type type: types) {
			ingredients = ingredientRepository.findByTypeIs(type);
			model.addAttribute(type.toString(), ingredients);
		}
	}
	
}













