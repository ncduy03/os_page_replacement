package algorithms;
	
import java.io.*;
import java.util.*;

import elements.Square;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class OPT extends PRAlgorithm {

    private int[] pageFrame;

    public OPT(int[] pageReferences, int frameSize, Pane inputPane) {
        super(pageReferences, frameSize, inputPane);
        pageFrame = new int[frameSize];
        for (int i = 0; i < frameSize; i++) {
            pageFrame[i] = -1;
        }
    }

    // Function to check if a page is present in frames
    private boolean search(int key) {
        for (int i = 0; i < frameSize; i++)
            if (pageFrame[i] == key)
                return true;
        return false;
    }

    // Function to find the farthest page in future
    private int predict(int index) {
        int res = -1, farthest = index;
        for (int i = 0; i < frameSize; i++) {
            int j;
            for (j = index; j < PRLength; j++) {
                if (pageFrame[i] == pageReferences[j]) {
                    if (j > farthest) {
                        farthest = j;
                        res = i;
                    }
                    break;
                }
            }

            // If a page is never referenced in future, return it.
            if (j == PRLength)
                return i;
        }

        // If all of the frames were not in future, return any of them, we return 0. Otherwise, we return res.
        return (res == -1) ? 0 : res;
    }

    @Override
    public int pageFaults() {
        int hit = 0;
        int index = 0;
        for (int i = 0; i < PRLength; i++) {

            int hitID = 0;
            // Page found in a frame: HIT
            if (search(pageReferences[i])) {
                hit++;
                hitID = 1;

                // Draw step
                drawPageFrames(pageFrame, hitID, i, frameSize, initColor, 60 + 100 * i, 65);

                continue;
            }

            // If there is space available in frames.
            if (index < frameSize)
                pageFrame[index++] = pageReferences[i];

            // Find the page to be replaced.
            else {
                int j = predict(i + 1);
                pageFrame[j] = pageReferences[i];
            }

            // Draw step
            drawPageFrames(pageFrame, hitID, i, frameSize, initColor, 60 + 100 * i, 65);
        }

        return PRLength - hit;
    }

    public void drawPageFrames(int[] frame, int hitID, int i, int frameSize, Color[] c, double startX, double startY) {
        // Draw page reference
        Square PR;
        PR = new Square(Integer.toString(pageReferences[i]), 60 + 100 * i, 5);
        pane.getChildren().add(PR);

        // Draw page frames
        int index = 0;
        for (Integer item : frame) {
            Square s;
            s = new Square(item, startX, startY + index * 60, c[0], c[1]);
            pane.getChildren().add(s);
            index++;
        }

        // Draw hit/miss
        Square Label;
        Label = new Square(hitID == 1 ? "Hit" : "Miss", 60 + 100 * i, 70 + frameSize * 60);
        pane.getChildren().add(Label);
    }

}
