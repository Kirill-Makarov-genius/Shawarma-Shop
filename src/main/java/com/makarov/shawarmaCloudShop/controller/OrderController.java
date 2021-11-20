package com.makarov.shawarmaCloudShop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.method.annotation.SessionAttributesHandler;

import com.makarov.shawarmaCloudShop.domain.Order;
import com.makarov.shawarmaCloudShop.domain.Shawarma;
import com.makarov.shawarmaCloudShop.repository.OrderRepository;

@Controller
@RequestMapping("/order")
@SessionAttributes({"cart"})
public class OrderController {
	
	
	private OrderRepository orderRepository;
	
	@Autowired
	OrderController(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	@ModelAttribute("cart")
	public List<Shawarma> createCart() {
		return new ArrayList<Shawarma>();
	}

	
	@GetMapping("/all")
	public String showAllOrders(Model model) {
		model.addAttribute("orders",orderRepository.findAll());
		return "order/showAllOrders";
	}
	
	
	
	@GetMapping("/add")
	public String formAddOrder(Order order, Model model) {
		model.addAttribute("order", order);
		System.out.println(model.getAttribute("cart"));
		return "order/formAddOrder";
	}
	
//FIX THIS. Problem in this constructor
	@PostMapping("/add")
	public String addOrder(@Valid Order order,
			Errors errors, Model model,
			HttpServletRequest request) {
		if (errors.hasErrors()) {
			return "order/formAddOrder";
		}
		@SuppressWarnings("unchecked")
		List<Shawarma> cart = (List<Shawarma>) model.asMap().get("cart");
		order.setShawarmasOrdered(cart);
		orderRepository.save(order);
		model.addAttribute("cart", new ArrayList<Shawarma>());
		return "redirect:/order/add";
	}
	
	
	
}
