package infnet.customer.management.api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collection;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import infnet.customer.management.api.model.domain.Cashier;
import infnet.customer.management.api.model.domain.service.CashierService;

@Component
public class CashierLoader implements ApplicationRunner {

	private final CashierService cashierService;

	public CashierLoader(CashierService cashierService) {
		this.cashierService = cashierService;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		FileReader file = new FileReader("cashiers.csv");
		try (BufferedReader reader = new BufferedReader(file)) {
			String line = reader.readLine();
			String fields[] = null;
			while (line != null) {
				fields = line.split(";");
				Cashier cashier = new Cashier();
				cashier.setName(fields[1]);
				cashier.setDocument(fields[2]);
				cashier.setRegistry(fields[3]);
				cashier.setEmail(fields[4]);
				cashier.setPhone(fields[5]);
				cashier.setType(Cashier.Type.valueOf(fields[6]));
				cashier.setActive(Boolean.valueOf(fields[7]));
				cashier.setSalary(Double.parseDouble(fields[8]));

				cashierService.register(cashier);
				line = reader.readLine();
			}
		}
		System.out.println("Loading cashier from csv file:");
		Collection<Cashier> customers = this.cashierService.getList();
		customers.forEach(System.out::println);
		System.out.println("--------------------------------");
	}

}
