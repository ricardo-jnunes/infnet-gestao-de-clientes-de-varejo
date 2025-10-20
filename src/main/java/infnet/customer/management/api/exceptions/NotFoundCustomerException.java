package infnet.customer.management.api.exceptions;

public class NotFoundCustomerException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotFoundCustomerException(String message) {
		super(message);
	}

}
