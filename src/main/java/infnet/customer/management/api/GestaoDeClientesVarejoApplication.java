package infnet.customer.management.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GestaoDeClientesVarejoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoDeClientesVarejoApplication.class, args);
	}

}
