package infnet.customer.management.api.interfaces;

import java.util.List;

public interface CRUDService<T, ID> {

	T register(T entity);

	T getById(ID id);

	List<T> getList();

	T edit(ID id, T entity);

	void delete(ID id);

}
