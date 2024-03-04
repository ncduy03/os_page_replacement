<<<<<<< HEAD
package algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedHashSet;
import java.util.Iterator;

import elements.Square;
import javafx.scene.layout.Pane;

public class LRU extends PRAlgorithm {
	
	private Queue<Integer> pageFrame;
	
	public LRU(int[] pageReferences, int frameSize, Pane inputPane) {
		super(pageReferences, frameSize, inputPane);
		pageFrame = new LinkedList<>();

	}

	public int pageFaults() {
	    int pageFaults = 0;
	    List<Integer> pages = new ArrayList<>();
	    List<Integer> recency = new ArrayList<>();

	    for (int i = 0; i < PRLength; i++) {
	        boolean isHit = pages.contains(pageReferences[i]);
	        if (isHit) {
	            // If the current page is already present in pages
	            int index = pages.indexOf(pageReferences[i]);
	            recency.set(index, i);
	        } else {
	            // Else, a page fault occurs
	            pageFaults++;
	            if (pages.size() == frameSize) {
	                // If pages contains the maximum number of pages
	                int lruIndex = recency.indexOf(Collections.min(recency));
	                pages.set(lruIndex, pageReferences[i]);
	                recency.set(lruIndex, i);
	            } else {
	                // If there is still space in pages, simply add the new page
	                pages.add(pageReferences[i]);
	                recency.add(i);
	            }
	        }

	        // Draw current step
	        drawStep(new LinkedList<>(pages), isHit ? 1 : 0, i, initColor, 60 + 100 * i, 65);
	    }

	    return pageFaults;
	}




}
=======
package algorithms;

import java.util.LinkedList;
import java.util.Queue;

import javafx.scene.layout.Pane;

public class LRU extends PRAlgorithm {
	
	private Queue<Integer> pageFrame;
	
	public LRU(int[] pageReferences, int frameSize, Pane inputPane) {
		super(pageReferences, frameSize, inputPane);
		pageFrame = new LinkedList<>();

	}
	public int pageFaults () {
		int pageFault = 0;
		
		for (int i = 0; i < PRLength; i++) {
			int hit = 1;
			if (pageFrame.isEmpty() || !pageFrame.contains(pageReferences[i])) {
		        if (pageFrame.size() == frameSize) // if frame is full we remove the least recently used page
		        	pageFrame.poll(); // removing the front element which is not replaced for long time
		 
		        pageFrame.add(pageReferences[i]); // now we add the page to frame
		        pageFault++; hit = 0; // incrementing page faults
		    }
			
		    else {
		    	// if the page already exists we'll remove it from previous position and add it to the end of the queue
		        pageFrame.remove(pageReferences[i]); 
		        pageFrame.add(pageReferences[i]);
				
			}
			
			//Draw current step
			// drawStep(pageFrame, hit, i, initColor, 60 + 100*i, 65);
			
		}
		return pageFault;
	}

	public static void main(String[] args) {
		int[] pages = {7, 0, 2, 4, 3, 1, 4, 7, 2, 0, 4, 3, 0, 3, 2, 7};
        int capacity = 3;
        LRU testLRU = new LRU(pages, capacity, null);
        System.out.println(testLRU.pageFaults());
	} 

}
>>>>>>> origin/chung/temp
