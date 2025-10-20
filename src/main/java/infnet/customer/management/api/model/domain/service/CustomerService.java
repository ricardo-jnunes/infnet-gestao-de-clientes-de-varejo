package infnet.customer.management.api.model.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import infnet.customer.management.api.exceptions.InvalidCustomerException;
import infnet.customer.management.api.exceptions.NotFoundCustomerException;
import infnet.customer.management.api.interfaces.CRUDService;
import infnet.customer.management.api.model.domain.Customer;

/**
 * 
 */
@Service
public class CustomerService implements CRUDService<Customer, Integer> {

	private final Map<Integer, Customer> map = new ConcurrentHashMap<>();
	private final AtomicInteger nextId = new AtomicInteger(1);

	private void validateCustomer(Customer entity) {
		// R1 - Nome não pode estar vazio
		if (entity == null || entity.getName().isBlank()) {
			throw new InvalidCustomerException("Name cannot be empty.");
		}
	}

	private void validateParameterId(Integer id) {
		// R1 - Id não pode ser vazio
		if (id == null || id <= 0) {
			throw new IllegalArgumentException("Id cannot be empty.");
		}
	}

	@Override
	public Customer register(Customer entity) {
		entity.setId(nextId.getAndIncrement());

		// TODO Buscar endereco a partir do CEP (VIA CEP)

		// R1 - Nome não pode estar vazio
		// R2 - Ao passar um id - o valor é ignorado e o Atomic Integer resolve
		// normalmente o próximo id
		validateCustomer(entity);

		map.put(entity.getId(), entity);
		return entity;
	}

	@Override
	public Customer getById(Integer id) {
		validateParameterId(id);

		Customer customer = map.get(id);
		if (customer == null) {
			throw new NotFoundCustomerException("Customer not found.");
		}
		return customer;
	}

	@Override
	public List<Customer> getCustomers() {
		return new ArrayList<Customer>(map.values());
	}

	@Override
	public Customer edit(Integer id, Customer entity) {
		validateCustomer(entity);
		validateParameterId(id);
		return null;
	}

	@Override
	public void delete(Integer id) {
		validateParameterId(id);
		map.remove(id);
	}

	public Customer setInactive(Integer id) {
		// R1 - Se o cliente está ativo, então desativar
		// Senão não fazer nada
		Customer customer = getById(id);
		if (customer.isActive()) {
			customer.setActive(false);
		}

		return customer;

	}

}
