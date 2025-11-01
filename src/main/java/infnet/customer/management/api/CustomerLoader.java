package infnet.customer.management.api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import infnet.customer.management.api.clients.ViaCepFeignClient;
import infnet.customer.management.api.model.domain.Address;
import infnet.customer.management.api.model.domain.Customer;
import infnet.customer.management.api.model.domain.service.CustomerService;
import infnet.customer.management.api.model.domain.viacep.AddressMapper;

@Component
public class CustomerLoader implements ApplicationRunner {

	private final CustomerService customerService;
	private final ViaCepFeignClient cepFeignClient;

	public CustomerLoader(CustomerService customerService, ViaCepFeignClient cepFeignClient) {
		this.customerService = customerService;
		this.cepFeignClient = cepFeignClient;
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

				String addresses = fields[7];
				String[] splitAddresses = addresses.split(",");
				List<Address> customerAddresses = new ArrayList<Address>();
				for (String splitAddress : splitAddresses) {
					Address address = AddressMapper.fromViaCep(cepFeignClient.findByCep(splitAddress));
					address.setCustomer(customer);
					customerAddresses.add(address);
				}

				customer.setAddresses(customerAddresses);
				customerService.register(customer);
				line = reader.readLine();
			}
		}
		System.out.println("Loading customer from csv file:");
		Collection<Customer> customers = this.customerService.getList();
		customers.forEach(System.out::println);
		System.out.println("--------------------------------");
	}

}
