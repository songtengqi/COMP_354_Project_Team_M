package eternity.exception;

public class EmptyInputException extends Exception {

	public EmptyInputException() {
		super("Empty input detected");
	}

	public EmptyInputException(String message) {
		super(message);
	}
}
