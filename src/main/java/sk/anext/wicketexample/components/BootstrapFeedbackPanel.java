package sk.anext.wicketexample.components;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

public class BootstrapFeedbackPanel extends FeedbackPanel {
	private static final long serialVersionUID = 1L;

	public BootstrapFeedbackPanel(String id) {
		super(id);
	}

	@Override
	protected void onConfigure() {
		super.onConfigure();

		get("feedbackul").add(new AttributeModifier("style", "margin:0px;"));

		setVisible(anyMessage());

		setOutputMarkupId(true);
		setOutputMarkupPlaceholderTag(true);

		if (anyMessage(FeedbackMessage.ERROR)) {
			add(new AttributeModifier("class", "alert alert-danger"));
		} else if (anyMessage(FeedbackMessage.SUCCESS)) {
			add(new AttributeModifier("class", "alert alert-success"));
		} else if (anyMessage(FeedbackMessage.INFO)) {
			add(new AttributeModifier("class", "alert alert-info"));
		} else {
			add(new AttributeModifier("class", "alert alert-success"));
		}
	}
}
