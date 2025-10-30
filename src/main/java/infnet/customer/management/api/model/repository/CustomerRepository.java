package infnet.customer.management.api.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import infnet.customer.management.api.model.domain.Customer;
import infnet.customer.management.api.model.domain.Person;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	Optional<Customer> findByDocument(String document);

	List<Customer> findByActive(Boolean isActive);

	List<Customer> findByTypeOrderByIdAsc(Person.Type type);
}
