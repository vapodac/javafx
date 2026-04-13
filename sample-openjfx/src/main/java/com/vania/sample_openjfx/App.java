package com.vania.sample_openjfx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;


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
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;



/**
 * JavaFX App
 */
public class App extends Application {
	Scene scene;
	BorderPane borderPane;
	StackPane topPane;
	StackPane middlePane;
	
	
    @Override
    public void start(Stage stage) {
    	
    	//setPrimaryStage(stage);
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();

        var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        
        //StackPane topPane = new StackPane(createButtonRow());
        // 1. Create the buttons
        Button button1 = new Button("Button 1");
        
        
        Label pathLabel = new Label("No file selected");
        Button selectButton = new Button("Select File");
        
        

        // 2. Setup FileChooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");

        // 3. Define Button Action
        selectButton.setOnAction(e -> {
            // showOpenDialog returns the selected File object
            File selectedFile = fileChooser.showOpenDialog(stage);
            
            if (selectedFile != null) {
                // 4. Update the Label with the file path
                pathLabel.setText("Selected: " + selectedFile.getAbsolutePath());
                middlePane = new StackPane(updateTableView(selectedFile.getAbsolutePath()));
                borderPane.setCenter(middlePane);
                
                
            } else {
                pathLabel.setText("File selection cancelled.");
                
            }
        });
        
        // The constructor can take child nodes and optional spacing.
        HBox hbox = new HBox(10); // 10 pixels spacing between children
        VBox vbox = new VBox(2);

        // Add buttons to the HBox
        hbox.getChildren().addAll(button1, selectButton);
        
        vbox.getChildren().addAll(hbox, pathLabel);
        
        // Optional: Set padding around the HBox and alignment.
        hbox.setPadding(new Insets(15, 15, 15, 15)); // Padding around the edges
        hbox.setAlignment(Pos.TOP_LEFT); // Center the buttons within the HBox
        topPane = new StackPane(vbox);
        
        topPane.setAlignment(Pos.TOP_LEFT);
        middlePane = new StackPane();
        
        
        // Add all to mainPane
        borderPane = new BorderPane();
        
        borderPane.setTop(topPane);
        borderPane.setCenter(middlePane);
        
        
        scene = new Scene(borderPane, 600,700);
     
        stage.setScene(scene);
        stage.show();
        
        
    }

    public static TreeTableView<Item> updateTableView(String filePath) {
    	TreeTableView<Item> treeTableView = new TreeTableView<Item>();

    	// Columns
    	TreeTableColumn<Item, String> treeTableColumn_type = new TreeTableColumn<>("Task");
    	TreeTableColumn<Item, String> treeTableColumn_name = new TreeTableColumn<>("Name");
    	TreeTableColumn<Item, Button> treeTableColumn_action = new TreeTableColumn<>("Action");
    	
    	

    	treeTableColumn_type.setCellValueFactory(new TreeItemPropertyValueFactory<>("type"));
    	treeTableColumn_type.setMinWidth(200);
    	
    	treeTableColumn_name.setCellValueFactory(new TreeItemPropertyValueFactory<>("name"));
    	treeTableColumn_name.setMinWidth(200);
    	
    	treeTableColumn_action.setCellValueFactory(new TreeItemPropertyValueFactory<>("action"));
    	treeTableColumn_action.setMinWidth(100);

    	treeTableView.getColumns().add(treeTableColumn_type);
    	treeTableView.getColumns().add(treeTableColumn_name);
    	treeTableView.getColumns().add(treeTableColumn_action);
    	
    	
    	// ----------CONFIGURABLE VIA JSON -------------------
    	
    	if (filePath != null ) {
    		TaskConfiguration taskConfiguration = loadTaskConfiguration(filePath);

        	// Top level category
        	TreeItem task_groups = new TreeItem(new Item(taskConfiguration.getTaskConfiguration(), "..."));
        	
        	
    		for (TaskCategory tc : taskConfiguration.getCategories()) {
    	
    			TreeItem categoryTreeItem = new TreeItem(new Item(tc.getCategory(), "..."));
    			
    		
    			for (TaskEntry t : tc.getTasks()) {
    				Item taskDetails = new Item(t.getTaskType(), t.getTaskName());
    				taskDetails.setInputArgs(t.getInputArgs());
    				TreeItem taskTreeItem = new TreeItem(taskDetails);
    				
    				categoryTreeItem.getChildren().add(taskTreeItem);
    			}
    			task_groups.getChildren().add(categoryTreeItem);
    			
    		}
    		treeTableView.setRoot(task_groups);
    		
    	}
    	
    	
    	return treeTableView;
    }
    	
    	


    
    public static TreeTableView<Item> createTableView() {
    	TreeTableView<Item> treeTableView = new TreeTableView<Item>();

    	// Columns
    	TreeTableColumn<Item, String> treeTableColumn_type = new TreeTableColumn<>("Task");
    	TreeTableColumn<Item, String> treeTableColumn_name = new TreeTableColumn<>("Name");
    	TreeTableColumn<Item, Button> treeTableColumn_action = new TreeTableColumn<>("Action");
    	
    	

    	treeTableColumn_type.setCellValueFactory(new TreeItemPropertyValueFactory<>("type"));
    	treeTableColumn_type.setMinWidth(200);
    	
    	treeTableColumn_name.setCellValueFactory(new TreeItemPropertyValueFactory<>("name"));
    	treeTableColumn_name.setMinWidth(200);
    	
    	treeTableColumn_action.setCellValueFactory(new TreeItemPropertyValueFactory<>("action"));
    	treeTableColumn_action.setMinWidth(100);

    	treeTableView.getColumns().add(treeTableColumn_type);
    	treeTableView.getColumns().add(treeTableColumn_name);
    	treeTableView.getColumns().add(treeTableColumn_action);
    	
    	
    	// ----------CONFIGURABLE VIA JSON -------------------
    	
    	// Task A tasks - row data
    	TreeItem TaskTypeA_1 = new TreeItem(new Item("TaskA 1", "A1"));
    	TreeItem TaskTypeA_2 = new TreeItem(new Item("TaskA 2", "A2"));
    	TreeItem TaskTypeA_3 = new TreeItem(new Item("TaskA 3", "A3"));

    	// Task A typ
    	TreeItem taskA_type = new TreeItem(new Item("Task A", "..."));
    	taskA_type.getChildren().add(TaskTypeA_1);
    	taskA_type.getChildren().add(TaskTypeA_2);
    	taskA_type.getChildren().add(TaskTypeA_3);
    	

    	// Task B tasks - row data
    	TreeItem TaskTypeB_1 = new TreeItem(new Item("TaskB 1", "B1"));
    	TreeItem TaskTypeB_2 = new TreeItem(new Item("TaskB 2", "B2"));
    	
    	
    	// Task B type
    	TreeItem taskB_type = new TreeItem(new Item("Task B", "..."));
    	taskB_type.getChildren().add(TaskTypeB_1);
    	taskB_type.getChildren().add(TaskTypeB_2);
    	
    	
    	// Top level category
    	TreeItem task_group = new TreeItem(new Item("Task Category ABC", "..."));
    	task_group.getChildren().add(taskA_type);
    	task_group.getChildren().add(taskB_type);

    	treeTableView.setRoot(task_group);
    	
    	return treeTableView;
    }

    
    private static TaskConfiguration loadTaskConfiguration(String path) {

    	TaskConfiguration taskConfig = null;
    	if (Files.exists(Paths.get(path))) {
			
		Gson gson = new Gson();

        // Read JSON from a file
        try (Reader reader = new FileReader(path.toString())) {

            // convert the JSON data to a Java object
            taskConfig = gson.fromJson(reader, TaskConfiguration.class);
            System.out.println(taskConfig);
            

        } catch (IOException e) {
            throw new RuntimeException(e);
        	}
    	}
    	
    	return taskConfig;
    }

}