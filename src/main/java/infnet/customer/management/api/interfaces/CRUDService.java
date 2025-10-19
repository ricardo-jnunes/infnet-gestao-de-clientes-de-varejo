package infnet.customer.management.api.interfaces;

import java.util.List;

public interface CRUDService<T, ID> {

	T register(T entity);

	List<T> getCustomers();

	T edit(ID id, T entity);
	
	void delete (ID id);

}
