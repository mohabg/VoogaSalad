package authoringEnvironment.settingsWindow.ObjectEditorFactory.Annotations;

import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * annotation used by VisualFactory that sets a custom name for an instance variable
 * @author joejacob
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SetFieldName {
	String label() default "";
}
