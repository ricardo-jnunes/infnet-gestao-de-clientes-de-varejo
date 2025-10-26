package infnet.customer.management.api.exceptions;

public class NotFoundCashierException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotFoundCashierException(String message) {
		super(message);
	}

}
