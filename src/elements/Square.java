package elements;

<<<<<<< HEAD
import javafx.animation.TranslateTransition;
=======
>>>>>>> origin/chung/temp
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
<<<<<<< HEAD
import javafx.scene.text.Text;
import javafx.util.Duration;
=======
>>>>>>> origin/chung/temp
import javafx.scene.paint.Color;

public class Square extends StackPane {
	private Rectangle rec = new Rectangle();
	private String input; 
	
	public String getInput() {
		return input;
	}

	public void setInputNumber(String input) {
		this.input = input;
	}
	
	public Square(int inputNumber, double x, double y, Color recFill, Color textFill) {
		this.input = Integer.toString(inputNumber);
		
		rec.setWidth(60.0f); 
	    rec.setHeight(60.0f); 
	    rec.setFill(recFill);
	    rec.setStroke(Color.BLACK);
	    getChildren().add(rec);
	    
	    Label label = new Label();
	    if (inputNumber >= 0) {
	    	label.setText(input);
	    	
	    } else {
	    	label.setText("");
	    }
	    label.setFont(Font.font("verdana", FontPosture.REGULAR, 14));;
	    label.setTextFill(textFill);
	    getChildren().add(label);
	    
	    setLayoutX(x);
	    setLayoutY(y);
	}

	public Square(String input_text, double x, double y) {
		rec.setWidth(60.0f); 
	    rec.setHeight(60.0f); 
	    rec.setFill(Color.WHITE);
	    rec.setStroke(Color.WHITE);
	    Label label = new Label();
	    label.setText(input_text);
	    label.setFont(Font.font("verdana", FontPosture.REGULAR, 16));;
	    getChildren().add(rec);
	    getChildren().add(label);
	    
	    setLayoutX(x);
	    setLayoutY(y);
	}
	
	public void setAxis(double X, double Y) {
		this.setLayoutX(X);
		this.setLayoutY(Y);
	}
	public void setFill(String fill) {
		rec.setFill(Color.web(fill,1.0));
	}
	

}
