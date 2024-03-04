package algorithms;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Queue;
import java.lang.Math;
import javafx.scene.layout.Pane;

public class LRFU extends PRAlgorithm {
	private PriorityQueue<Integer> pageFrame;
	private HashMap <Integer, Integer> lastAccessedTime = new HashMap <> ();
	private HashMap <Integer, Double> CRF = new HashMap<>();
	private static double control = 0.5;
	private static double base = Math.pow(0.5, control);
	private static int i;
	
	public LRFU(int[] pageReferences, int frameSize, Pane inputPane) {
		super(pageReferences, frameSize, inputPane);
		this.pageFrame = new PriorityQueue<>(
			Comparator.comparing(a -> F(lastAccessedTime.get(a) - i) * CRF.get(a))
		);
	}
	
	public static double F(int x) {
		return Math.pow(base, x);
		
	}
	public void replaceRoot(int page) {
		if (pageFrame.size() == frameSize) // if frame is full we remove the least recently used page
        	pageFrame.poll(); // removing the front element which is not replaced for long time
        pageFrame.add(page); // now we add the page to frame
	}
	public void restore(int page) {
		// if the page already exists we'll remove it from previous position and add it to the end of the queue
        pageFrame.remove(page); 
        pageFrame.add(page);
	}
	
	public int pageFaults () {
		int pageFault = 0;
		int hit;
		int page;
		for (i = 0; i < PRLength; i++) {
			page = pageReferences[i];
			lastAccessedTime.put(page, lastAccessedTime.getOrDefault(page, -1));
			CRF.put(page, CRF.getOrDefault(page, F(0)));
			
			
			if (pageFrame.isEmpty() || !pageFrame.contains(page)) {
				CRF.put(page, F(0));
				lastAccessedTime.put(page, i);
		        replaceRoot(page);
		        pageFault++; // incrementing page faults
		        hit = 0; 
		    }
			
		    else {
		    	CRF.put(page, 1.0 + F(i - lastAccessedTime.get(page)) * CRF.get(page));
		    	lastAccessedTime.put(page, i);
		    	restore(page);
		        hit = 1;
			}
			
			//drawStep(pageFrame, hit, i, initColor, 60 + 100*i, 65);
		}
		return pageFault;
	}

	public static void main(String[] args) {
		int[] pages = {7, 0, 2, 4, 3, 1, 4, 7, 2, 0, 4, 3, 0, 3, 2, 7};

		int capacity = 3;
        LRFU testLFU = new LRFU(pages, capacity, null);
        System.out.println(testLFU.pageFaults());
	} 

}
