package authoringEnvironment.settingsWindow.ObjectEditorFactory.Exceptions;

/**
 * exception used for VisualFactory utility methods that's thrown when an 
 * unsupported object tries to get a corresponding editing gui object
 * @author joejacob, inspired by Robert Duvall
 */
public final class FieldTypeException extends RuntimeException {
	
    private static final long serialVersionUID = 1L;

     /**
     * makes an exception with a message formed by message and args
     * @param message
     * @param args
     */
    public FieldTypeException (String message, Object ... args) {
        super(formatException(message, args));
    }

    /**
     * throws exception
     * @param exception
     */
    public FieldTypeException (Throwable exception) {
        super(exception);
    }


    /**
     * creates an exception with the caught exception and a new message
     * @param cause
     * @param message
     * @param args
     */
    public FieldTypeException (Throwable cause, String message, Object ... args) {
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
