package infnet.customer.management.api.model.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import infnet.customer.management.api.exceptions.InvalidCashierException;
import infnet.customer.management.api.exceptions.NotFoundCashierException;
import infnet.customer.management.api.interfaces.CRUDService;
import infnet.customer.management.api.model.domain.Cashier;

/**
 * 
 */
@Service
public class CashierService implements CRUDService<Cashier, Integer> {

	private final Map<Integer, Cashier> map = new ConcurrentHashMap<>();
	private final AtomicInteger nextId = new AtomicInteger(1);

	private void validateCashier(Cashier entity) {
		// R1 - Nome não pode estar vazio
		if (entity == null || entity.getName().isBlank()) {
			throw new InvalidCashierException("Name cannot be empty.");
		}
	}

	private void validateParameterId(Integer id) {
		// R1 - Id não pode ser vazio
		if (id == null || id <= 0) {
			throw new IllegalArgumentException("Id cannot be empty.");
		}
	}

	@Override
	public Cashier register(Cashier entity) {
		entity.setId(nextId.getAndIncrement());

		// R1 - Nome não pode estar vazio
		// R2 - Ao passar um id - o valor é ignorado e o Atomic Integer resolve
		// normalmente o próximo id
		validateCashier(entity);

		map.put(entity.getId(), entity);
		return entity;
	}

	@Override
	public Cashier getById(Integer id) {
		validateParameterId(id);

		Cashier cashier = map.get(id);
		if (cashier == null) {
			throw new NotFoundCashierException("Cahier not found.");
		}
		return cashier;
	}

	@Override
	public List<Cashier> getList() {
		return new ArrayList<Cashier>(map.values());
	}

	@Override
	public Cashier edit(Integer id, Cashier entity) {
		validateCashier(entity);
		validateParameterId(id);
		
		entity.setId(id);
		
		map.put(entity.getId(), entity);
		return entity;
	}

	@Override
	public void delete(Integer id) {
		validateParameterId(id);
		map.remove(id);
	}

	public Cashier setInactive(Integer id) {
		// R1 - Se o vendedor está ativo, então desativar
		// Senão não fazer nada
		Cashier cashier = getById(id);
		if (cashier.isActive()) {
			cashier.setActive(false);
		}

		return cashier;

	}

}
