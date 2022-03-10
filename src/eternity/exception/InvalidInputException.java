package eternity.exception;

public class InvalidInputException extends Exception {

	public InvalidInputException() {
		super("Invalid input detected");
	}

	public InvalidInputException(String message) {
		super(message);
	}
}
