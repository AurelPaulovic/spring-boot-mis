package sk.anext.wicketexample.components;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.model.Model;

public class ErrorDecorationBehavior extends AttributeModifier {
	private static final long serialVersionUID = 1L;

	private String replaceClassName;

	public ErrorDecorationBehavior(String errorClassName) {
		super("class", new Model<String>(errorClassName));
	}

	public ErrorDecorationBehavior(String replaceClassName, String errorClassName) {
		super("class", new Model<String>(errorClassName));

		this.replaceClassName = replaceClassName;
	}

	@Override
	protected String newValue(final String currentValue, final String replacementValue) {
		if (replaceClassName != null) {
			return currentValue.replace(replaceClassName, replacementValue);
		}
		return currentValue + replacementValue;
	}

	@Override
	public boolean isEnabled(Component component) {
		return super.isEnabled(component) && component.hasErrorMessage();
	}
}
