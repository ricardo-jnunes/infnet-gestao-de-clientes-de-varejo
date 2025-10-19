package infnet.customer.management.api.model.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import infnet.customer.management.api.interfaces.CRUDService;
import infnet.customer.management.api.model.domain.Customer;

@Service
public class CustomerService implements CRUDService<Customer, Integer> {

	private final Map<Integer, Customer> map = new ConcurrentHashMap<>();
	private final AtomicInteger nextId = new AtomicInteger(1);

	@Override
	public Customer register(Customer entity) {
		entity.setId(nextId.getAndIncrement());
		map.put(entity.getId(), entity);
		return entity;
	}

	@Override
	public List<Customer> getCustomers() {
		return new ArrayList<Customer>(map.values());
	}

	@Override
	public Customer edit(Integer id, Customer entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		map.remove(id);
	}

}
