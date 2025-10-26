package infnet.customer.management.api.exceptions.handler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import infnet.customer.management.api.exceptions.InvalidCashierException;
import infnet.customer.management.api.exceptions.NotFoundCashierException;

@ControllerAdvice
public class GlobalExcpetionHandler {

	@ExceptionHandler(InvalidCashierException.class)
	public ResponseEntity<Map<String, String>> handleVendedorInvalidoException(InvalidCashierException e) {
		Map<String, String> mapa = new HashMap<String, String>();

		mapa.put("timestamp", LocalDateTime.now().toString());
		mapa.put("status", HttpStatus.BAD_REQUEST.toString());
		mapa.put("error", e.getMessage());
		mapa.put("detail", "Verifique os dados fornecidos para o vendedor!");

		return new ResponseEntity<Map<String, String>>(mapa, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NotFoundCashierException.class)
	public ResponseEntity<Map<String, String>> handleVendedorNaoEncontratoException(NotFoundCashierException e) {
		Map<String, String> mapa = new HashMap<String, String>();

		mapa.put("timestamp", LocalDateTime.now().toString());
		mapa.put("status", HttpStatus.NOT_FOUND.toString());
		mapa.put("error", e.getMessage());
		mapa.put("detail", "O vendedor solicitado não foi encontrado!");

		return new ResponseEntity<Map<String, String>>(mapa, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException e) {
		Map<String, String> mapa = new HashMap<String, String>();

		mapa.put("timestamp", LocalDateTime.now().toString());
		mapa.put("status", HttpStatus.BAD_REQUEST.toString());
		mapa.put("error", e.getMessage());
		mapa.put("detail", "Argumento ou parâmetro inválido na operação!");

		return new ResponseEntity<Map<String, String>>(mapa, HttpStatus.BAD_REQUEST);
	}
}
