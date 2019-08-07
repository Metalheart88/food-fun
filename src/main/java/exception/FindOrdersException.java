package exception;

public class FindOrdersException extends RuntimeException {

    public FindOrdersException(String message, Throwable cause) {
        super(message, cause);
    }

    public FindOrdersException(String message) {
        super(message);
    }
}

