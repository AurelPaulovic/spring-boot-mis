package sk.anext.wicketexample.pages;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.markup.html.WebMarkupContainer;
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
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.StringValidator;

import sk.anext.wicketexample.components.BootstrapFeedbackPanel;
import sk.anext.wicketexample.components.ErrorDecorationBehavior;
import sk.anext.wicketexample.service.ExampleService;

public class AnotherPage extends WebPage 
{
	
	@SpringBean
	private ExampleService exampleService;
	
	private String stringRangeInput;
	private Integer integerInput;
	private String stringDropDown;
	private boolean booleanCheckBox;
	
	
	public AnotherPage() {
		initPage();
	}
		
	private void initPage() {
		
		stringDropDown = exampleService.getDropDownChoiceData().get(0);
		
		Form<AnotherPage> form = new Form<AnotherPage>("exampleForm", new CompoundPropertyModel<AnotherPage>(this));
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
		
		form.add(new DropDownChoice<String>("stringDropDown", exampleService.getDropDownChoiceData()));
		
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
		
		
		
		
		add(new ListView<String>("list", getModel()) {
			protected void populateItem(ListItem<String> item) 
			{
				item.add(new Label("value1", item.getIndex()));
				item.add(new Label("value2", item.getModelObject()));
			};
		});
		
		
		WebMarkupContainer pagingListContainer = new WebMarkupContainer("pagingListContainer");
		pagingListContainer.setOutputMarkupId(true);
		add(pagingListContainer);
		
		DataView<String> dataView = new DataView<String>("pagingList", new PagingDataProvider(), 5) {
			
			@Override
			protected void populateItem(Item<String> item) {
				
				item.add(new Label("value1", item.getIndex()));
				item.add(new Label("value2", item.getModelObject()));
			}
		};
		pagingListContainer.add(dataView);
		
		pagingListContainer.add(new AjaxPagingNavigator("dataViewNavigator", dataView) 
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void onAjaxEvent(AjaxRequestTarget target) {
				super.onAjaxEvent(target);
				//target.add(feedbackPanel);
			}
		});

		
	}
	
	private IModel<ArrayList<String>> getModel()
	{
		return new LoadableDetachableModel<ArrayList<String>>() {
			
			@Override
			protected ArrayList<String> load() {
				
				return (ArrayList)exampleService.getTableData();
			}
		};
	}
	
	class PagingDataProvider implements IDataProvider<String>
	{

		@Override
		public void detach() {
			
		}

		@Override
		public Iterator<? extends String> iterator(long first, long count) {
			
			int last = (int)(first+count);
			
			if(last > size())
			{
				last = (int)size();
			}
			
			return exampleService.getTableData().subList((int)first, last).iterator();
		}

		@Override
		public long size() {
			return exampleService.getTableData().size();
		}

		@Override
		public IModel<String> model(String object) {
			
			return new Model<String>(object);
		}
		
	}
}
