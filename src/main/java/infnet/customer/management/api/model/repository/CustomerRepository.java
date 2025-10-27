package infnet.customer.management.api.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import infnet.customer.management.api.model.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
