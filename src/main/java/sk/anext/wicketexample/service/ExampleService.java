package sk.anext.wicketexample.service;

import java.util.List;

public interface ExampleService {
	
	public List<String> getDropDownChoiceData();
	public List<String> getDropDownChoiceData(String input);
	public List<String> getTableData();

}
