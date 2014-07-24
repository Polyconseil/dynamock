package fr.polyconseil.mock.dynamock.exception;

public class MockNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MockNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public MockNotFoundException(String message) {
		super(message);
	}

}
