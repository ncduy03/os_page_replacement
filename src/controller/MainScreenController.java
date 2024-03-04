package controller;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import algorithms.SecondChance;
import algorithms.FIFO;
import algorithms.LRFU;
import algorithms.LRU;
import algorithms.OPT;
import algorithms.PRAlgorithm;
import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class MainScreenController implements Initializable{
	
	public static String sort;
	PRAlgorithm algo;
	Stage stage;
	private String currentAlgo;
	private String[] algoList = {
			"First In First Out (FIFO)",
			"Optimal Page Replacement",
			"Least Recently Used (LRU)",
			"Least Frequency Used (LFU)",
			"Second Chance and Clock (2nd chance)"
	};
	private int[] pageReferences;
	private int frameSize;
	
	
    @FXML
    private Button resetButton;
    
	@FXML
    private TextArea pageFaultArea;
	
	@FXML
	private ChoiceBox<String> algoChoiceBox;
	
	@FXML
	private Pane mainPane;
	
	@FXML
    private TextField noFramesField;

    @FXML
    private TextField pageReferencesField;
	
    @FXML
    private Button exitButton;

    @FXML
    private Button helpButton;

    @FXML
    private BorderPane myborderpane;

    @FXML
    private Button startButton;


    @FXML
    void exitButtonPressed(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText("You're about to exit!");
		alert.setContentText("See you next time  !!!");
		
		if(alert.showAndWait().get() == ButtonType.OK){
		stage = (Stage) myborderpane.getScene().getWindow();
			stage.close();}
    }

    @FXML
    void helpButtonPressed(ActionEvent event) {
    	new HelpController();
    }
    
    @FXML
    void resetButtonPressed(ActionEvent event) {
    	mainPane.getChildren().clear();
    	Label PFLabel = new Label("Page Frames");
    	PFLabel.setLayoutX(-30); PFLabel.setLayoutY(150); 
    	PFLabel.setRotate(90);
    	PFLabel.setFont(Font.font("verdana", FontPosture.REGULAR, 20));
    	mainPane.getChildren().add(PFLabel);
    }


    @FXML
    void startButtonPressed(ActionEvent event) {
    	mainPane.getChildren().clear();
    	
    	Label PFLabel = new Label("Page Frames");
    	PFLabel.setLayoutX(-30); PFLabel.setLayoutY(150); 
    	PFLabel.setRotate(90);
    	PFLabel.setFont(Font.font("verdana", FontPosture.REGULAR, 20));
    	mainPane.getChildren().add(PFLabel);
    	
    	
    	try {
    		currentAlgo = algoChoiceBox.getValue();

	    	frameSize = Integer.parseInt(noFramesField.getText().trim());
	    	if (frameSize > 5) {
	    		throw new Exception("Number of frames must be less than or equals 5");
	    	}
	    	
	    	String strPR = pageReferencesField.getText().trim();
	    	String[] PRArray = strPR.split(",");
	    	if (PRArray.length > 13) {
	    		throw new Exception("The length of page reference string is too long to display");
	    	}
	    	
	    	
    		pageReferences = new int[PRArray.length];
    		for (int i = 0; i < PRArray.length; i++) {
    			pageReferences[i] = Integer.parseInt(PRArray[i].trim());
    		}
    		
    		if (currentAlgo.equals("First In First Out (FIFO)")) {
    			algo = new SecondChance(pageReferences, frameSize, mainPane);
    		}
    		
    		else if (currentAlgo.equals("Optimal Page Replacement")) {
    			algo = new OPT(pageReferences, frameSize, mainPane);
    		}
    		
    		else if (currentAlgo.equals("Least Recently Used (LRU)")) {
    			algo = new LRU(pageReferences, frameSize, mainPane);
    		}
    		
    		else if (currentAlgo.equals("Least Frequency Used (LFU)")) {
    			algo = new LRFU(pageReferences, frameSize, mainPane);
    		}
    		else if (currentAlgo.equals("Second Chance and Clock (2nd chance)")) {
    			algo = new SecondChance(pageReferences, frameSize, mainPane);
    		}
    
 
    		int pageFault = algo.pageFaults();
    		pageFaultArea.setText("Total Page Faults = " + pageFault);
    		
			
    	} catch (Exception e) {
    		Alert input_error = new Alert(AlertType.ERROR);
    		input_error.setTitle("ERROR");
    		input_error.setHeaderText("Invalid input");
    		input_error.setContentText(e.getMessage());
    		input_error.show();
    		e.printStackTrace();
    	}
		
    }
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		algoChoiceBox.getItems().addAll(algoList);
		algoChoiceBox.setOnAction(this::getAlgo);
		algoChoiceBox.setValue("First In First Out (FIFO)");
		Label PFLabel = new Label("Page Frames");
    	PFLabel.setLayoutX(-30); PFLabel.setLayoutY(150); 
    	PFLabel.setRotate(90);
    	PFLabel.setFont(Font.font("verdana", FontPosture.REGULAR, 20));
    	mainPane.getChildren().add(PFLabel);
		
	}
	
	public String getAlgo(ActionEvent event) {
		return algoChoiceBox.getValue();
	}
		
	}

   






