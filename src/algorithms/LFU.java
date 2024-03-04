package algorithms;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import javafx.scene.layout.Pane;

public class LFU extends PRAlgorithm {
	private PriorityQueue<Integer> pageFrame;
	private HashMap <Integer, Integer> freqUsedCounter = new HashMap <> ();
	private HashMap <Integer, Integer> lastAccessedTime = new HashMap <> ();
	
	public LFU(int[] pageReferences, int frameSize, Pane inputPane) {
		super(pageReferences, frameSize, inputPane);
        this.pageFrame = new PriorityQueue<>((a, b) -> {
        	if (freqUsedCounter.get(a) != freqUsedCounter.get(b))
            	return freqUsedCounter.get(a) - freqUsedCounter.get(b);
        	else {
        		return lastAccessedTime.get(a) - lastAccessedTime.get(b);
        	}
        });

	}
	public int pageFaults () {
		int pageFault = 0;
		int hit;
	
		for (int i = 0; i < PRLength; i++) {
			freqUsedCounter.put(pageReferences[i], freqUsedCounter.getOrDefault(pageReferences[i], 0) + 1);
			lastAccessedTime.put(pageReferences[i], i);
			if (pageFrame.isEmpty() || !pageFrame.contains(pageReferences[i])) {
		        if (pageFrame.size() == frameSize) // if frame is full we remove the least recently used page
		        	pageFrame.poll(); // removing the front element which is not replaced for long time
		        
		        pageFrame.add(pageReferences[i]); // now we add the page to frame
		        pageFault++; // incrementing page faults
		        hit = 0; 
		    }
			
		    else {
		    	// if the page already exists we'll remove it from previous position and add it to the end of the queue
		        pageFrame.remove(pageReferences[i]); 
		        pageFrame.add(pageReferences[i]);
		        hit = 1;
			}
			
			// drawStep(pageFrame, hit, i, initColor, 60 + 100*i, 65);
		}
		return pageFault;
	}

	public static void main(String[] args) {
		int[] pages = {7, 0, 2, 4, 3, 1, 4, 7, 2, 0, 4, 3, 0, 3, 2, 7};

		int capacity = 3;
        LFU testLFU = new LFU(pages, capacity, null);
        System.out.println(testLFU.pageFaults());
	} 

}
