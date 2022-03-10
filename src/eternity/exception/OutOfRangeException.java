package eternity.exception;

public class OutOfRangeException extends Exception {

	public OutOfRangeException() {
		super("Out of range.");
	}

	public OutOfRangeException(String message) {
		super(message);
	}
}
