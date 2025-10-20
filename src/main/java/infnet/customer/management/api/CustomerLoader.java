package infnet.customer.management.api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collection;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import infnet.customer.management.api.model.domain.Address;
import infnet.customer.management.api.model.domain.Customer;
import infnet.customer.management.api.model.domain.service.CustomerService;

@Component
public class CustomerLoader implements ApplicationRunner {

	private final CustomerService customerService;

	public CustomerLoader(CustomerService customerService) {
		this.customerService = customerService;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		FileReader file = new FileReader("customers.csv");
		try (BufferedReader reader = new BufferedReader(file)) {
			String line = reader.readLine();
			String fields[] = null;
			while (line != null) {
				fields = line.split(";");
				Customer customer = new Customer();
				customer.setName(fields[1]);
				customer.setDocument(fields[2]);
				customer.setEmail(fields[3]);
				customer.setPhone(fields[4]);
				customer.setType(Customer.Type.valueOf(fields[5]));
				customer.setActive(Boolean.valueOf(fields[6]));

				Address address = new Address();
				address.setCep(fields[7]);
				address.setNeighborhood("Centro");
				address.setStreet("Rua Exemplo");
				address.setNumber(123);
				address.setState("Rio de Janeiro");
				address.setCountry("Brasil");
				customer.setAddress(address);

				customerService.register(customer);
				line = reader.readLine();
			}
		}
		System.out.println("Loading customer from csv file:");
		Collection<Customer> customers = this.customerService.getCustomers();
		customers.forEach(System.out::println);
		System.out.println("--------------------------------");
	}

}
