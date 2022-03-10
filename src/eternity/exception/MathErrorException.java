package eternity.exception;

public class MathErrorException extends Exception {

	public MathErrorException() {
		super();
	}

	public MathErrorException(String message) {
		super("MATH ERROR");
	}
}
