package infnet.customer.management.api.controllers;

import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import infnet.customer.management.api.model.domain.Customer;
import infnet.customer.management.api.model.domain.Person;
import infnet.customer.management.api.model.domain.service.CustomerService;
import infnet.customer.management.api.model.dtos.CustomerDTO;
import infnet.customer.management.api.model.dtos.CustomerMapper;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class CustomerController {

	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping("/customers")
	public ResponseEntity<?> getCustomers(@RequestParam(required = false) String document,
			@RequestParam(required = false) String type) {

		if (document != null && !document.trim().isEmpty()) {
			Customer customer = customerService.findByDocument(document);
			CustomerDTO customerDTO = CustomerMapper.toDTO(customer);

			return ResponseEntity.ok(customerDTO);
		}

		if (type != null && !type.trim().isEmpty()) {
			List<CustomerDTO> customers = customerService.findByTypeOrderByIdAsc(Person.Type.valueOf(type)).stream()
					.map(CustomerMapper::toDTO).collect(Collectors.toList());

			return ResponseEntity.ok(customers);
		}

		List<CustomerDTO> customers = customerService.getList().stream().map(CustomerMapper::toDTO)
				.collect(Collectors.toList());
		if (customers.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(customers);
	}

	@GetMapping("/customers/{id}")
	public ResponseEntity<CustomerDTO> getById(@PathVariable Integer id) {
		Customer customer = customerService.getById(id);
		CustomerDTO customerDTO = CustomerMapper.toDTO(customer);
		return ResponseEntity.ok(customerDTO);
	}

	@GetMapping("/customers/actives/{isActive}")
	public ResponseEntity<List<CustomerDTO>> getByActive(@PathVariable Boolean isActive) {
		List<CustomerDTO> customers = customerService.findByActive(isActive).stream().map(CustomerMapper::toDTO)
				.collect(Collectors.toList());

		if (customers.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(customers);
	}

	@PostMapping("/customers")
	public ResponseEntity<CustomerDTO> register(@Valid @RequestBody CustomerDTO customerDTO) {
		Customer customer = CustomerMapper.toEntity(customerDTO);
		Customer registered = customerService.register(customer);
		return ResponseEntity.status(HttpStatus.CREATED).body(CustomerMapper.toDTO(registered));
	}

	@PutMapping("/customers/{id}")
	public ResponseEntity<CustomerDTO> edit(@PathVariable Integer id, @RequestBody CustomerDTO customerDTO) {
		Customer customer = CustomerMapper.toEntity(customerDTO);
		Customer edited = customerService.edit(id, customer);
		return ResponseEntity.ok(CustomerMapper.toDTO(edited));
	}

	@DeleteMapping("/customers/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		customerService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PatchMapping("/customers/{id}/inactivate")
	public ResponseEntity<CustomerDTO> inactivate(@PathVariable Integer id) {
		Customer customer = customerService.setInactive(id);
		CustomerDTO customerDTO = CustomerMapper.toDTO(customer);
		return ResponseEntity.ok(customerDTO);

	}
}
