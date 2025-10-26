package infnet.customer.management.api.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import infnet.customer.management.api.model.domain.viacep.ViaCepResponse;
import io.swagger.v3.oas.annotations.Hidden;

@Hidden
@FeignClient(name = "viacep", url = "http://viacep.com.br/ws/")
public interface ViaCepFeignClient {

	@GetMapping("/{cep}/json/")
	ViaCepResponse findByCep(@PathVariable String cep);
}