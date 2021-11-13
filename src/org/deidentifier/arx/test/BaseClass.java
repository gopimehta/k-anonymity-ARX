package org.deidentifier.arx.test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.deidentifier.arx.ARXLattice.ARXNode;
import org.deidentifier.arx.ARXResult;
import org.deidentifier.arx.Data;
import org.deidentifier.arx.DataHandle;

/**
 * This class provided by ARX serves as a base class for implementing other classes as described in the offical ARX documentation.
 */

public abstract class BaseClass {

    /**
     * Prints a given data handle.
     *
     * @param handle
     */
    protected static void print(DataHandle handle) {
        final Iterator<String[]> itHandle = handle.iterator();
        print(itHandle);
    }

    /**
     * Prints a given iterator.
     *
     * @param iterator
     */
    protected static void print(Iterator<String[]> iterator) {
        while (iterator.hasNext()) {
            System.out.print("   ");
            System.out.println(Arrays.toString(iterator.next()));
        }
    }

    
    /**
     * Prints the result.
     *
     * @param result
     * @param data
     */
    protected static void printResult(final ARXResult result, final Data data) {

        // Print time
        final DecimalFormat df1 = new DecimalFormat("#####0.00");
        final String sTotal = df1.format(result.getTime() / 1000d) + "s";
        System.out.println(" - Time needed: " + sTotal);

        // Extract
        final ARXNode optimum = result.getGlobalOptimum();
        final List<String> qis = new ArrayList<String>(data.getDefinition().getQuasiIdentifyingAttributes());

        if (optimum == null) {
            System.out.println(" - No solution found!");
            return;
        }

        // Initialize
        final StringBuffer[] identifiers = new StringBuffer[qis.size()];
        final StringBuffer[] generalizations = new StringBuffer[qis.size()];
        int lengthI = 0;
        int lengthG = 0;
        for (int i = 0; i < qis.size(); i++) {
            identifiers[i] = new StringBuffer();
            generalizations[i] = new StringBuffer();
            identifiers[i].append(qis.get(i));
            generalizations[i].append(optimum.getGeneralization(qis.get(i)));
            if (data.getDefinition().isHierarchyAvailable(qis.get(i)))
                generalizations[i].append("/").append(data.getDefinition().getHierarchy(qis.get(i))[0].length - 1);
            lengthI = Math.max(lengthI, identifiers[i].length());
            lengthG = Math.max(lengthG, generalizations[i].length());
        }

        // Padding
        for (int i = 0; i < qis.size(); i++) {
            while (identifiers[i].length() < lengthI) {
                identifiers[i].append(" ");
            }
            while (generalizations[i].length() < lengthG) {
                generalizations[i].insert(0, " ");
            }
        }

        // Print
        System.out.println(" - Information loss: " + result.getGlobalOptimum().getLowestScore() + " / " + result.getGlobalOptimum().getHighestScore());
        System.out.println(" - Optimal generalization");
        for (int i = 0; i < qis.size(); i++) {
            System.out.println("   * " + identifiers[i] + ": " + generalizations[i]);
        }
        System.out.println(" - Statistics");
        System.out.println(result.getOutput(result.getGlobalOptimum(), false).getStatistics().getEquivalenceClassStatistics());
    }
}
