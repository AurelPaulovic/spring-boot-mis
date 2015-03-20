package sk.anext.wicketexample.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

@Service("exampleService")
public class ExampleServiceImpl implements ExampleService {
	
	private List<String> dropDownChoiceData;
	private List<String> tableData;
	
	@PostConstruct
	public void initialize() {
		dropDownChoiceData = new ArrayList<String>();
		dropDownChoiceData.add("Choice #1");
		dropDownChoiceData.add("Choice #2");
		dropDownChoiceData.add("Choice #3");
		dropDownChoiceData.add("Choice #4");
		dropDownChoiceData.add("Choice #5");
		dropDownChoiceData.add("Choice #6");
		
		tableData = new ArrayList<String>();
		tableData.add("Data #1");
		tableData.add("Data #2");
		tableData.add("Data #3");
		tableData.add("Data #4");
		tableData.add("Data #5");
		tableData.add("Data #6");
	}

	@Override
	public List<String> getDropDownChoiceData() {
		return dropDownChoiceData;
	}

	@Override
	public List<String> getTableData() {
		return tableData;
	}
	
	

}
