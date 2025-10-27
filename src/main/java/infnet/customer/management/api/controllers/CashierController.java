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

import infnet.customer.management.api.model.domain.Cashier;
import infnet.customer.management.api.model.domain.service.CashierService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class CashierController {

	private final CashierService cashierService;

	public CashierController(CashierService cashierService) {
		this.cashierService = cashierService;
	}

	@GetMapping("/cashiers")
	public ResponseEntity<List<Cashier>> getCashiers() {
		List<Cashier> list = cashierService.getList();
		if (list.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(list);
	}

	@GetMapping("/cashiers/{id}")
	public ResponseEntity<Cashier> getById(@PathVariable Integer id) {
		Cashier cashierById = cashierService.getById(id);
		return ResponseEntity.ok(cashierById);
	}

	@PostMapping("/cashiers")
	public ResponseEntity<Cashier> register(@Valid @RequestBody Cashier cashier) {
		Cashier registered = cashierService.register(cashier);
		return ResponseEntity.status(HttpStatus.CREATED).body(registered);
	}

	@PutMapping("/cashiers/{id}")
	public ResponseEntity<Cashier> edit(@PathVariable Integer id, @RequestBody Cashier cashier) {
		Cashier edited = cashierService.edit(id, cashier);
		return ResponseEntity.ok(edited);
	}

	@DeleteMapping("/cashiers/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		cashierService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PatchMapping("/cashiers/{id}/inactivate")
	public ResponseEntity<Cashier> inactivate(@PathVariable Integer id) {
		Cashier cashierInactive = cashierService.setInactive(id);
		return ResponseEntity.ok(cashierInactive);

	}

}
