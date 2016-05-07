package authoringEnvironment.settingsWindow.ObjectEditorFactory.Exceptions;

/**
 * exception used for ReflectUtils utility methods
 * @author joejacob, inspired by Robert Duvall
 */
public class ReflectUtilsException extends RuntimeException {
	
    private static final long serialVersionUID = 1L;

    /**
     * makes an exception with a message formed by message and args
     * @param message
     * @param args
     */
    public ReflectUtilsException (String message, Object ... args) {
        super(formatException(message, args));
    }

    /**
     * throws exception
     * @param exception
     */
    public ReflectUtilsException (Throwable exception) {
        super(exception);
    }

    /**
     * creates an exception with the caught exception and a new message
     * @param cause
     * @param message
     * @param args
     */
    public ReflectUtilsException (Throwable cause, String message, Object ... args) {
        super(formatException(message, args), cause);
    }
    
    /**
     * formats exception message
     * @param message
     * @param args
     * @return
     */
    private static String formatException(String message, Object... args) {
    	return String.format(message, args);
    }
}