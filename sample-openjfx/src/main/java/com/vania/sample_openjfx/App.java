package com.vania.sample_openjfx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import com.vania.sample_openjfx.*;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();

        var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        
        StackPane topPane = new StackPane(createButtonRow());
        topPane.setAlignment(Pos.TOP_LEFT);
        StackPane middlePane = new StackPane(createTableView());
        
        
        // Add all to mainPane
        BorderPane borderPane = new BorderPane();
        
        borderPane.setTop(topPane);
        borderPane.setCenter(middlePane);
        
        
        var scene = new Scene(borderPane, 500,700);
     
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    
    
    public static HBox createButtonRow() {
        // 1. Create the buttons
        Button button1 = new Button("Button Number 1");
        Button button2 = new Button("Button Number 2");

        // Add action handlers (optional)
        button1.setOnAction(e -> System.out.println("Button 1 clicked!"));
        button2.setOnAction(e -> System.out.println("Button 2 clicked!"));

        // The constructor can take child nodes and optional spacing.
        HBox hbox = new HBox(10); // 10 pixels spacing between children
        
        // Add buttons to the HBox
        hbox.getChildren().addAll(button1, button2);
        
        // Optional: Set padding around the HBox and alignment.
        hbox.setPadding(new Insets(15, 15, 15, 15)); // Padding around the edges
        hbox.setAlignment(Pos.TOP_LEFT); // Center the buttons within the HBox

        return hbox;
        
    }
    
    public static TreeTableView<Item> createTableView() {
    	TreeTableView<Item> treeTableView = new TreeTableView<Item>();

    	TreeTableColumn<Item, String> treeTableColumn1 = new TreeTableColumn<>("Type");
    	TreeTableColumn<Item, String> treeTableColumn2 = new TreeTableColumn<>("Name");
    	TreeTableColumn<Item, Button> treeTableColumn3 = new TreeTableColumn<>("Action");

    	treeTableColumn1.setCellValueFactory(new TreeItemPropertyValueFactory<>("type"));
    	treeTableColumn1.setMinWidth(200);
    	
    	treeTableColumn2.setCellValueFactory(new TreeItemPropertyValueFactory<>("name"));
    	treeTableColumn2.setMinWidth(200);
    	
    	treeTableColumn3.setCellValueFactory(new TreeItemPropertyValueFactory<>("action"));
    	treeTableColumn3.setMinWidth(100);
    	

    	treeTableView.getColumns().add(treeTableColumn1);
    	treeTableView.getColumns().add(treeTableColumn2);
    	treeTableView.getColumns().add(treeTableColumn3);
    	
    	
    	TreeItem mercedes1 = new TreeItem(new Item("Mercedes", "SL500"));
    	TreeItem mercedes2 = new TreeItem(new Item("Mercedes", "SL500 AMG"));
    	TreeItem mercedes3 = new TreeItem(new Item("Mercedes", "CLA 200"));

    	TreeItem mercedes = new TreeItem(new Item("Mercedes", "..."));
    	mercedes.getChildren().add(mercedes1);
    	mercedes.getChildren().add(mercedes2);

    	TreeItem audi1 = new TreeItem(new Item("Audi", "A1"));
    	TreeItem audi2 = new TreeItem(new Item("Audi", "A5"));
    	TreeItem audi3 = new TreeItem(new Item("Audi", "A7"));

    	TreeItem audi = new TreeItem(new Item("Audi", "..."));
    	audi.getChildren().add(audi1);
    	audi.getChildren().add(audi2);
    	audi.getChildren().add(audi3);

    	TreeItem cars = new TreeItem(new Item("Cars", "..."));
    	cars.getChildren().add(audi);
    	cars.getChildren().add(mercedes);

    	treeTableView.setRoot(cars);
    	
    	return treeTableView;
    }

}