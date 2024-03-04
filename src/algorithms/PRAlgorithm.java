package algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import elements.Square;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public abstract class PRAlgorithm {
	protected int[] pageReferences;
	protected int frameSize;
	public static Pane pane;
	protected int PRLength;
	protected Color[] initColor = {Color.LIGHTBLUE, Color.BLACK };
	
	public PRAlgorithm(int[] pageReferences, int frameSize, Pane inputPane) {
		super();
		this.pageReferences = pageReferences;
		this.frameSize = frameSize;
		this.PRLength = pageReferences.length;
		pane = inputPane;
	}
	
	public void drawStep(Queue<Integer> pageFrame, int hit, int i, Color[] c, double startX, double startY) {
		
		List<Integer> frame = new ArrayList<Integer>(pageFrame);
		while (frame.size() < frameSize) {
			frame.add(-1);
		}
		
		//Draw page reference
		Square PR;
		PR = new Square(Integer.toString(pageReferences[i]), 60 + 100*i, 5);
		pane.getChildren().add(PR);
		
		//Draw page frames
		int index = 0; 
		for (Integer item: frame) {
            Square s;
			s = new Square(item, startX, startY + index * 60, c[0], c[1]);
			pane.getChildren().add(s);
			index++;
        }
		
		//Draw hit/miss
		Square Label;
		Label = new Square(hit == 1 ? "Hit" : "Miss", 60 + 100*i, 70 + frameSize*60);
		pane.getChildren().add(Label);
	
		}

	public abstract int pageFaults();
	
	
}
