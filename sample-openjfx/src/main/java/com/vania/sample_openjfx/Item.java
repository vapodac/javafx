package com.vania.sample_openjfx;

import javafx.scene.control.Button;

public class Item {

    private String type = null;
    private String name = null;
    private Button action = null;

    public Item() {
    }

    public Item(String type, String name) {
        this.type = type;
        this.name = name;
        if (!this.name.equals("...")) {
        	this.action = new Button("action");
        	this.action.setOnAction(e -> System.out.println("Button for : " + this.name + " clicked!"));
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