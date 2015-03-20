package sk.anext.wicketexample.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.validation.validator.RangeValidator;
import org.apache.wicket.validation.validator.StringValidator;

import sk.anext.wicketexample.components.BootstrapFeedbackPanel;
import sk.anext.wicketexample.components.ErrorDecorationBehavior;

public class ExamplePage extends WebPage 
{
	private static final List<String> dropDownCoices = new ArrayList<String>();
	static {
		dropDownCoices.add("Choice #1");
		dropDownCoices.add("Choice #2");
		dropDownCoices.add("Choice #3");
		dropDownCoices.add("Choice #4");
		dropDownCoices.add("Choice #5");
		dropDownCoices.add("Choice #6");
	}
	
	private String stringRangeInput;
	private Integer integerInput;
	private String stringDropDown;
	private boolean booleanCheckBox;
	
	
	public ExamplePage() {
		init();
	}
		
	private void init() {
		
		stringDropDown = dropDownCoices.get(0);
		
		Form<ExamplePage> form = new Form<ExamplePage>("exampleForm", new CompoundPropertyModel<ExamplePage>(this));
		add(form);
		
		final FeedbackPanel feedbackPanel = new BootstrapFeedbackPanel("feedbackPanel");
		feedbackPanel.setOutputMarkupId(true);
		form.add(feedbackPanel);
		
		form.add(new RequiredTextField<String>("stringRangeInput")
				.add(new StringValidator(2, 20))
				.add(new ErrorDecorationBehavior("error")));
		
		form.add(new TextField<Integer>("integerInput")
				.setRequired(true)
				.add(new ErrorDecorationBehavior("error")));
		
		form.add(new DropDownChoice<String>("stringDropDown", dropDownCoices));
		
		form.add(new CheckBox("booleanCheckBox"));
		
		form.add(new AjaxSubmitLink("submitButton") 
		{
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				
				info("State: stringRangeInput='" + stringRangeInput + "'; integerInput='" + integerInput + "'; stringDropDown='" + stringDropDown + "'; booleanCheckBox='" + booleanCheckBox + "'");
				
				target.add(feedbackPanel);
			}
			
			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				
				target.add(feedbackPanel);
			}
		});
		
		
		
		add(new ReusableComponentPanel("reusable1"));
		add(new ReusableComponentPanel("reusable2"));
		
		
		
		add(new ListView<String>("list", dropDownCoices) {
			protected void populateItem(ListItem<String> item) 
			{
				item.add(new Label("value1", item.getIndex()));
				item.add(new Label("value2", item.getModelObject()));
			};
		});
	}
}
