package infnet.customer.management.api.exceptions.handler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import infnet.customer.management.api.exceptions.InvalidCashierException;
import infnet.customer.management.api.exceptions.InvalidCustomerException;
import infnet.customer.management.api.exceptions.NotFoundCashierException;
import infnet.customer.management.api.exceptions.NotFoundCustomerException;

@ControllerAdvice
public class GlobalExcpetionHandler {

	@ExceptionHandler(InvalidCashierException.class)
	public ResponseEntity<Map<String, String>> handleInvalidCashierException(InvalidCashierException e) {
		Map<String, String> mapa = new HashMap<String, String>();

		mapa.put("timestamp", LocalDateTime.now().toString());
		mapa.put("status", HttpStatus.BAD_REQUEST.toString());
		mapa.put("error", e.getMessage());
		mapa.put("detail", "Verifique os dados fornecidos para o vendedor!");

		return new ResponseEntity<Map<String, String>>(mapa, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NotFoundCashierException.class)
	public ResponseEntity<Map<String, String>> handleNotFoundCashierException(NotFoundCashierException e) {
		Map<String, String> mapa = new HashMap<String, String>();

		mapa.put("timestamp", LocalDateTime.now().toString());
		mapa.put("status", HttpStatus.NOT_FOUND.toString());
		mapa.put("error", e.getMessage());
		mapa.put("detail", "O vendedor solicitado não foi encontrado!");

		return new ResponseEntity<Map<String, String>>(mapa, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidCustomerException.class)
	public ResponseEntity<Map<String, String>> handleInvalidCustomerException(InvalidCustomerException e) {
		Map<String, String> mapa = new HashMap<String, String>();

		mapa.put("timestamp", LocalDateTime.now().toString());
		mapa.put("status", HttpStatus.BAD_REQUEST.toString());
		mapa.put("error", e.getMessage());
		mapa.put("detail", "Verifique os dados informados pelo cliente!");

		return new ResponseEntity<Map<String, String>>(mapa, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NotFoundCustomerException.class)
	public ResponseEntity<Map<String, String>> handleNotFoundCustomerException(NotFoundCustomerException e) {
		Map<String, String> mapa = new HashMap<String, String>();

		mapa.put("timestamp", LocalDateTime.now().toString());
		mapa.put("status", HttpStatus.NOT_FOUND.toString());
		mapa.put("error", e.getMessage());
		mapa.put("detail", "O cliente solicitado não foi encontrado!");

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
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Map<String, String>> handleDataIntegrityViolationException(DataIntegrityViolationException e){
		Map<String, String> mapa = new HashMap<String, String>();

		mapa.put("timestamp", LocalDateTime.now().toString());
		mapa.put("status", HttpStatus.CONFLICT.toString());
		mapa.put("error", e.getMessage());
		mapa.put("detail", "Problema no momento de criar um recurso duplicado!");
		
		return new ResponseEntity<Map<String,String>>(mapa, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException e){
		Map<String, String> mapa = new HashMap<String, String>();

		mapa.put("timestamp", LocalDateTime.now().toString());
		mapa.put("status", HttpStatus.INTERNAL_SERVER_ERROR.toString());
		mapa.put("error", e.getMessage());
		mapa.put("detail", "Ocorreu um erro interno no servidor!");
		
		return new ResponseEntity<Map<String,String>>(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
