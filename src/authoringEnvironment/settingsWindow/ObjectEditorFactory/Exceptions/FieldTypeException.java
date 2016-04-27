package authoringEnvironment.settingsWindow.ObjectEditorFactory.Exceptions;

public final class FieldTypeException extends RuntimeException {
	 // for serialization
    private static final long serialVersionUID = 1L;

    /**
     * Create an exception based on an issue in our code.
     */
    public FieldTypeException (String message, Object ... args) {
        super(format(message, args));
    }

    /**
     * Create an exception based on a caught exception.
     */
    public FieldTypeException (Throwable exception) {
        super(exception);
    }

    /**
     * Create an exception based on a caught exception with a different message.
     */
    public FieldTypeException (Throwable cause, String message, Object ... args) {
        super(format(message, args), cause);
    }

    // remove duplicate code, also placeholder for future improvements (like logging)
    private static String format (String message, Object ... args) {
        return String.format(message, args);
    }
}
