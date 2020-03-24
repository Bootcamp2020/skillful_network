package fr.uca.cdr.skillful_network.exceptions;

// TODO: Auto-generated Javadoc
/**
 * The Class ResourceNotFoundException.
 */
public class ResourceNotFoundException extends RuntimeException{

   /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates a new resource not found exception.
	 */
	public ResourceNotFoundException() {
        super();
    }
    
    /**
     * Instantiates a new resource not found exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Instantiates a new resource not found exception.
     *
     * @param message the message
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
    
    /**
     * Instantiates a new resource not found exception.
     *
     * @param cause the cause
     */
    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }
}
