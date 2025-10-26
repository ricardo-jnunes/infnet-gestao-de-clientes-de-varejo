package infnet.customer.management.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		return customerService.getList();
	}

	@GetMapping("/customers/{id}")
	public Customer getById(@PathVariable Integer id) {
		return customerService.getById(id);
	}

	@PostMapping("/customers")
	public Customer register(@RequestBody Customer customer) {
		return customerService.register(customer);
	}

	@PutMapping("/customers/{id}")
	public Customer edit(@PathVariable Integer id, @RequestBody Customer customer) {
		return customerService.edit(id, customer);
	}

	@DeleteMapping("/customers/{id}")
	public void delete(@PathVariable Integer id) {
		customerService.delete(id);
	}

	@PatchMapping("/customers/{id}/inactivate")
	public Customer inactivate(@PathVariable Integer id) {
		return customerService.setInactive(id);

	}

}
