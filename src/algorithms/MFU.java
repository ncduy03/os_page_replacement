package algorithms;

import java.util.*;
import javafx.scene.layout.Pane;

public class MFU extends PRAlgorithm {
    private Map<Integer, Integer> pageFrequency;
    private LinkedList<Integer> pageFrame;
    private int PRLength;

    public MFU(int[] pageReferences, int frameSize, Pane inputPane) {
        super(pageReferences, frameSize, inputPane);
        this.pageFrequency = new HashMap<>();
        this.pageFrame = new LinkedList<>();
        this.PRLength = pageReferences.length;
    }

    public int pageFaults() {
        int pageFault = 0;

        for (int i = 0; i < PRLength; i++) {
            int hit = 1;
            if (pageFrame.isEmpty() || !pageFrame.contains(pageReferences[i])) {
                if (pageFrame.size() == frameSize) {
                    int maxFreqPage = getMaxFreqPage();
                    int idx = pageFrame.indexOf(maxFreqPage);
                    pageFrame.remove(idx);
                    pageFrame.add(idx, pageReferences[i]);
                    pageFrequency.remove(maxFreqPage);
                } else {
                    pageFrame.add(pageReferences[i]);
                }
                pageFault++;
                hit = 0;
            }
            pageFrequency.put(pageReferences[i], pageFrequency.getOrDefault(pageReferences[i], 0) + 1);
            drawStep((Queue<Integer>) pageFrame, hit, i, initColor, 60 + 100*i, 65);
        }

        return pageFault;
    }


    private int getMaxFreqPage() {
        int maxFreq = -1;
        int maxFreqPage = -1;
        int earliestIndex = PRLength;
        for (int page : pageFrame) {
            int freq = pageFrequency.get(page);
            int index = pageFrame.indexOf(page);
            if (freq > maxFreq || (freq == maxFreq && index < earliestIndex)) {
                maxFreq = freq;
                maxFreqPage = page;
                earliestIndex = index;
            }
        }
        return maxFreqPage;
    }
}









