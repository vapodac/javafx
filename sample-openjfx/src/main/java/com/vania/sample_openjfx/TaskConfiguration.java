package com.vania.sample_openjfx;

import java.util.ArrayList;
import java.util.List;

public class TaskConfiguration {
	
	private String taskConfiguration;
	private List<TaskCategory> categories = new ArrayList();
	public String getTaskConfiguration() {
		return taskConfiguration;
	}
	public void setTaskConfiguration(String taskConfiguration) {
		this.taskConfiguration = taskConfiguration;
	}

	
	
	public List<TaskCategory> getCategories() {
		return categories;
	}
	public void setCategories(List<TaskCategory> categories) {
		this.categories = categories;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		
		StringBuffer sb = new StringBuffer();
		sb.append("Configuratioon: " + taskConfiguration + "\n");
		for (TaskCategory category : categories)  {
			sb.append(category.toString());
			sb.append("\n");
			
		}
		return sb.toString();
	}

}
