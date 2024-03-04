package algorithms;

import java.util.*;

import javafx.scene.layout.Pane;

public class SecondChance extends PRAlgorithm {
	private LinkedList<Integer> pageFrame; // The queue to store the pages in memory
	private boolean[] secondChance; // The array to store the second chance bit of each page
	private int pointer; // The pointer to track the candidate for replacement
	
	public SecondChance(int[] pageReferences, int frameSize, Pane inputPane) {
		super(pageReferences, frameSize, inputPane);
		pageFrame = new LinkedList<Integer>();
		secondChance = new boolean[frameSize]; // Initialize the second chance array with false values
		// Arrays.fill(secondChance, false);
		pointer = 0;
	}

	public int pageFaults() {
		int pageFaults = 0;
		int hit; 
		for (int i = 0; i < PRLength; i++) {
			hit = 1;
			if (pageFrame.size() < frameSize) { // If the cache is not full yet
    		   if (!pageFrame.contains(pageReferences[i])) { // If the page is not in memory
    			   pageFrame.add(pageReferences[i]); // Add the page to the queue
    			   secondChance[pointer] = true; // Set the second chance bit to true
    			   pointer = (pointer + 1) % frameSize; // Update the pointer to the next position
    			   pageFaults++; hit = 0; // Increase the page fault count and set the hit flag to false
    		   }
    		   else { // If the page is already in memory
    			   int index = pageFrame.indexOf(pageReferences[i]); // Find the index of the page in the queue
    			   secondChance[index] = true; // Set the second chance bit to true
    		   }
			}
			else { // If the cache is full
				if (!pageFrame.contains(pageReferences[i])) { // If the page is not in memory
					while (secondChance[pointer]) { // While the page pointed by the pointer has a second chance
						secondChance[pointer] = false; // Reset the second chance bit to false
						pointer = (pointer + 1) % frameSize; // Move the pointer to the next position
					}
					pageFrame.remove(pointer); // Remove the page pointed by the pointer from the queue
					pageFrame.add(pointer, pageReferences[i]); // Add the new page to the queue at the same position
					secondChance[pointer] = true; // Set the second chance bit to true
					pointer = (pointer + 1) % frameSize; // Update the pointer to the next position
					pageFaults++; hit = 0; // Increase the page fault count and set the hit flag to false
				}
				else { // If the page is already in memory
					int index = pageFrame.indexOf(pageReferences[i]); // Find the index of the page in the queue
					secondChance[index] = true; // Set the second chance bit to true
				}
			}
			// System.out.println(pageFrame);
			
			//Draw current step
			drawStep(pageFrame, hit, i, initColor, 60 + 100*i, 65);
			
		}
			return pageFaults;
    }
	
	
	
	public static void main(String args[]) {
		int[] pages = {0, 4, 1, 4, 2, 4, 3, 4, 2, 4, 0, 4, 1, 4, 2, 4, 3, 4};
        int capacity = 3;
        SecondChance test1 = new SecondChance(pages, capacity, null);
        System.out.println(test1.secondChance);
        System.out.println(test1.pageFaults());
	}
}
