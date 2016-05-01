package authoringEnvironment.settingsWindow.ObjectEditorFactory.Exceptions;

public class ReflectUtilsException extends RuntimeException {
	 // for serialization
    private static final long serialVersionUID = 1L;

    /**
     * Create an exception based on an issue in our code.
     */
    public ReflectUtilsException (String message, Object ... args) {
        super(format(message, args));
    }

    /**
     * Create an exception based on a caught exception.
     */
    public ReflectUtilsException (Throwable exception) {
        super(exception);
    }

    /**
     * Create an exception based on a caught exception with a different message.
     */
    public ReflectUtilsException (Throwable cause, String message, Object ... args) {
        super(format(message, args), cause);
    }

    // remove duplicate code, also placeholder for future improvements (like logging)
    private static String format (String message, Object ... args) {
        return String.format(message, args);
    }
}