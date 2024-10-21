package es.uma.informatica.misia.ae.simpleea;

import java.util.Random;

public class Knapsack implements Problem {
    private int[] weights;
    private int[] values;
    private int capacity;
    private double penaltyFactor;

    public Knapsack(int[] weights, int[] values, int capacity, double penaltyFactor) {
        this.weights = weights;
        this.values = values;
        this.capacity = capacity;
        this.penaltyFactor = penaltyFactor;
    }

    public double evaluate(Individual individual) {
        BinaryString binaryString = (BinaryString) individual;
        int totalWeight = 0;
        int totalValue = 0;

        for (int i = 0; i < binaryString.getChromosome().length; i++) {
            if (binaryString.getChromosome()[i] == 1) {
                totalWeight += weights[i];
                totalValue += values[i];
            }
        }

        if (totalWeight <= capacity) {
            return totalValue;
        } else {
            int overWeight = totalWeight - capacity;
            return totalValue - (penaltyFactor * overWeight);
        }
    }

    public BinaryString generateRandomIndividual(Random rnd) {
        return new BinaryString(values.length,rnd);
    }

}
