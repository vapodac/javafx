package com.vania.sample_openjfx;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskCategory {
	
	private String category;
	private List<TaskEntry> tasks = new ArrayList();
	
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<TaskEntry> getTasks() {
		return tasks;
	}

	public void setTasks(List<TaskEntry> tasks) {
		this.tasks = tasks;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		
		StringBuffer sb = new StringBuffer();
		for (TaskEntry task : tasks)  {
			sb.append(task.toString());
			sb.append("\n");
			
		}
		return sb.toString();
	}

	
	
}
