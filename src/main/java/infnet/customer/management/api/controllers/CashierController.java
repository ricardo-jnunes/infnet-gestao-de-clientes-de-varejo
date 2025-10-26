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

import infnet.customer.management.api.model.domain.Cashier;
import infnet.customer.management.api.model.domain.service.CashierService;

@RestController
@RequestMapping("/api")
public class CashierController {

	private final CashierService cashierService;

	public CashierController(CashierService cashierService) {
		this.cashierService = cashierService;
	}

	@GetMapping("/cashiers")
	public List<Cashier> getCashiers() {
		return cashierService.getList();
	}

	@GetMapping("/cashiers/{id}")
	public Cashier getById(@PathVariable Integer id) {
		return cashierService.getById(id);
	}

	@PostMapping("/cashiers")
	public Cashier register(@RequestBody Cashier cashier) {
		return cashierService.register(cashier);
	}

	@PutMapping("/cashiers/{id}")
	public Cashier edit(@PathVariable Integer id, @RequestBody Cashier cashier) {
		return cashierService.edit(id, cashier);
	}

	@DeleteMapping("/cashiers/{id}")
	public void delete(@PathVariable Integer id) {
		cashierService.delete(id);
	}

	@PatchMapping("/cashiers/{id}/inactivate")
	public Cashier inactivate(@PathVariable Integer id) {
		return cashierService.setInactive(id);

	}

}
