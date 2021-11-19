package com.makarov.shawarmaCloudShop.controller;

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
@SessionAttributes(value = {"order", "curShawarma"})
public class OrderController {
	
	
	private OrderRepository orderRepository;
	
	@Autowired
	OrderController(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	@ModelAttribute("order")
	public Order createOrder() {
		return new Order();
	}
	@ModelAttribute("curShawarma")
	public Shawarma getCurShawarma() {
		return new Shawarma();
	}
	
	@GetMapping("/all")
	public String showAllOrders(Model model) {
		model.addAttribute("orders",orderRepository.findAll());
		return "order/showAllOrders";
	}
	
	
	
	@GetMapping("/add")
	public String formAddOrder(@ModelAttribute("order") Order order, Model model) {
		model.addAttribute("order", order);
		return "order/formAddOrder";
	}
	
	@PostMapping("/add")
	public String addOrder(@Valid @ModelAttribute("order") Order order, Errors errors, Model model) {
		if (errors.hasErrors()) {
			return "order/formAddOrder";
		}
		order.getShawarmasOrdered().add((Shawarma)model.getAttribute("curShawarma"));
		orderRepository.save(order);
		return "redirect:/order/add";
	}
	
	
	
}
