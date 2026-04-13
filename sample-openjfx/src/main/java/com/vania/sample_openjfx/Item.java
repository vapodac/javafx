package com.vania.sample_openjfx;

import java.net.URL;
import java.util.Map;

import javafx.scene.control.Button;

public class Item {

    private String type = null;
    private String name = null;
    private Button action = null;
    private Map<String, String> inputArgs;

    public Map<String, String> getInputArgs() {
		return inputArgs;
	}

	public void setInputArgs(Map<String, String> inputArgs) {
		this.inputArgs = inputArgs;
	}

	public Item() {
    }

    public Item(String type, String name) {
        this.type = type;
        this.name = name;
        if (!this.name.equals("...")) {
        	this.action = new Button("action");
        	this.action.setOnAction(e -> System.out.println("Button for : " + this.name + " clicked!\ninputArgs: " + "[ " + inputArgs.toString() + "]" ));
        	
        
        }
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Button getAction() {
		return action;
	}

	public void setAction(Button action) {
		this.action = action;
	}
    
}