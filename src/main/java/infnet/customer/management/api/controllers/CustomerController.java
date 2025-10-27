package infnet.customer.management.api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class CustomerController {

	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getCustomers() {
		List<Customer> list = customerService.getList();
		if (list.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(list);
	}

	@GetMapping("/customers/{id}")
	public ResponseEntity<Customer> getById(@PathVariable Integer id) {
		Customer customerById = customerService.getById(id);
		return ResponseEntity.ok(customerById);
	}

	@PostMapping("/customers")
	public ResponseEntity<Customer> register(@Valid @RequestBody Customer customer) {
		Customer registered = customerService.register(customer);
		return ResponseEntity.status(HttpStatus.CREATED).body(registered);
	}

	@PutMapping("/customers/{id}")
	public ResponseEntity<Customer> edit(@PathVariable Integer id, @RequestBody Customer customer) {
		Customer edited = customerService.edit(id, customer);
		return ResponseEntity.ok(edited);
	}

	@DeleteMapping("/customers/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		customerService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PatchMapping("/customers/{id}/inactivate")
	public ResponseEntity<Customer> inactivate(@PathVariable Integer id) {
		Customer customerInactive = customerService.setInactive(id);
		return ResponseEntity.ok(customerInactive);

	}
}
