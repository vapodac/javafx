package com.vania.sample_openjfx;

import java.util.Map;

public class TaskEntry {
	
    private String taskType;
    private String taskName;
    private Map<String, String> inputArgs; //  Map
     
    
   
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public Map<String, String> getInputArgs() {
		return inputArgs;
	}
	public void setInputArgs(Map<String, String> inputArgs) {
		this.inputArgs = inputArgs;
	}
	
	@Override
	public String toString() {
		
		return  taskType + " ( " + taskName + " ) " + "[ " + inputArgs.toString() + "]";
	
	}

	
	
}
