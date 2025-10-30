package infnet.customer.management.api.model.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import infnet.customer.management.api.exceptions.InvalidCustomerException;
import infnet.customer.management.api.exceptions.NotFoundCustomerException;
import infnet.customer.management.api.interfaces.CRUDService;
import infnet.customer.management.api.model.domain.Customer;
import infnet.customer.management.api.model.domain.Person;
import infnet.customer.management.api.model.repository.CustomerRepository;

/**
 * 
 */
@Service
public class CustomerService implements CRUDService<Customer, Integer> {

	private final CustomerRepository customerRepository;

	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	private void validateCustomer(Customer entity) {
		// R1 - Nome não pode estar vazio
		if (entity == null) {
			throw new InvalidCustomerException("Informações do cliente inválido.");
		}
	}

	private void validateParameterId(Integer id) {
		// R1 - Id não pode ser vazio
		if (id == null || id <= 0) {
			throw new IllegalArgumentException("Necessário informar um id.");
		}
	}

	@Override
	public Customer register(Customer entity) {
		validateCustomer(entity);

		if (entity.getId() != null && entity.getId() > 0) {
			throw new IllegalArgumentException("O cadastro do cliente não pode ter um ID!");
		}

		return customerRepository.save(entity);
	}

	@Override
	public Customer getById(Integer id) {
		validateParameterId(id);

		return customerRepository.findById(id)
				.orElseThrow(() -> new NotFoundCustomerException("O vendedor com o ID [" + id + "] não encontrado!"));
	}

	public Customer findByDocument(String document) {
		if (document == null || document.trim().isEmpty()) {
			throw new IllegalArgumentException("O CPF utilizado na busca do vendedor não pode ser nulo ou vazio.");
		}

		return customerRepository.findByDocument(document).orElseThrow(
				() -> new NotFoundCustomerException("O cliente de documento [" + document + "] não foi encontrado!"));
	}

	@Override
	public List<Customer> getList() {
		return customerRepository.findAll();
	}

	@Override
	public Customer edit(Integer id, Customer entity) {
		validateCustomer(entity);
		validateParameterId(id);

		entity.setId(id);
		return customerRepository.save(entity);
	}

	@Override
	public void delete(Integer id) {
		validateParameterId(id);
		Customer customerById = getById(id);
		customerRepository.delete(customerById);
	}

	public Customer setInactive(Integer id) {
		// R1 - Se o cliente está ativo, então desativar
		// Senão não fazer nada
		Customer customerById = getById(id);
		if (customerById.isActive()) {
			customerById.setActive(false);
		}

		return customerRepository.save(customerById);

	}

	public List<Customer> findByActive(Boolean isActive) {
		return customerRepository.findByActive(isActive);
	}

	public List<Customer> findByTypeOrderByIdAsc(Person.Type type) {
		return customerRepository.findByTypeOrderByIdAsc(type);
	}

}
