package controller;
import java.io.File;
import java.util.Scanner;

import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;  
public class HelpController   {  
	public HelpController(){  
		try  {  
			BorderPane pane = new BorderPane();
			
		    TextArea ta = new TextArea();
		    ta.setEditable(false);
		     
		    ScrollPane scrollPane = new ScrollPane(ta);
		    scrollPane.setFitToHeight(true);
		    scrollPane.setFitToWidth(true);
		    
		    pane.setCenter(scrollPane);
			Scene scene = new Scene(pane, 500, 500);
			Scanner input = new Scanner(new File(
					"E:\\OSProject_PageReplacement\\PageReplacement\\src\\controller\\Help.txt"));

			while (input.hasNext()) {
	               ta.appendText(input.nextLine() + '\n');
			}
			
			Stage stage = new Stage();
			stage.setTitle("Instructions");
			stage.setScene(scene);
			//stage.setHeight(500);
			//stage.setWidth(800);
			
			stage.show();
			
			
			} catch(Exception e)  
		{  
			e.printStackTrace();  
		}  
}  
}  