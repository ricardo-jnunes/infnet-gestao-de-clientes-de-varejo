package infnet.customer.management.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import infnet.customer.management.api.model.domain.Customer;
import infnet.customer.management.api.model.domain.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerController {

	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping("/customers")
	public List<Customer> getCustomers() {
		return customerService.getCustomers();
	}

	@PostMapping("/customers")
	public Customer register(@RequestBody Customer customer) {
		return customerService.register(customer);
	}

}
