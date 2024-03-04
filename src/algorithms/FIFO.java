package algorithms;

import java.util.*;

<<<<<<< HEAD
import elements.Square;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
=======
import javafx.scene.layout.Pane;
>>>>>>> origin/chung/temp

public class FIFO extends PRAlgorithm {
	
	private Queue<Integer> pageFrame;
	
	
	public FIFO(int[] pageReferences, int frameSize, Pane inputPane) {
		super(pageReferences, frameSize, inputPane);
		pageFrame = new LinkedList<>();
	}

	public int pageFaults() {
		
		int pageFaults = 0;
		
		for (int i = 0; i < PRLength; i++) {
			int hit = 1;
			if (pageFrame.size() < frameSize) {
    		   if (!pageFrame.contains(pageReferences[i])) {
    			   pageFrame.add(pageReferences[i]);
    			   pageFaults++; hit = 0;
    		   }
			}
			else {
				if (!pageFrame.contains(pageReferences[i])) {
<<<<<<< HEAD
					pageFrame.poll();
=======
					pageFrame.poll(); // Pop the queue 
>>>>>>> origin/chung/temp
					pageFrame.add(pageReferences[i]);
					pageFaults++; hit = 0;
				}
			}
<<<<<<< HEAD
  
=======
			// System.out.println(""pageFrame);
			
>>>>>>> origin/chung/temp
			//Draw current step
			drawStep(pageFrame, hit, i, initColor, 60 + 100*i, 65);
			
		}
			
			return pageFaults;
    }
	
	
	
<<<<<<< HEAD
	/*public static void main(String args[])
	{
		int pages[] = {0, 1, 2, 3, 0, 1, 4, 0, 1, 2, 3, 4};
        int capacity = 3;
        FIFO test1 = new FIFO(pages, capacity, null);
        test1.pageFaults();
	} */
=======
	public static void main(String args[]) {
		int[] pages = {7, 0, 2, 4, 3, 1, 4, 7, 2, 0, 4, 3, 0, 3, 2, 7};
        int capacity = 3;
        FIFO test1 = new FIFO(pages, capacity, null);
        System.out.println(test1.pageFaults());
	}
>>>>>>> origin/chung/temp
}

