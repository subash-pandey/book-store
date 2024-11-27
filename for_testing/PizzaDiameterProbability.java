import org.apache.commons.math3.distribution.NormalDistribution;

public class PizzaDiameterProbability {

    public static void main(String[] args) {

        // Mean and standard deviation of pizza diameters
        double mean = 16.3;
        double sd = 0.2;

        // Range of diameters
        double lowerBound = 15.95;
        double upperBound = 16.63;

        // Create a NormalDistribution object
        NormalDistribution normalDistribution = new NormalDistribution(mean, sd);

        // Calculate the probability
        double probability = normalDistribution.cumulativeProbability(upperBound) -
                normalDistribution.cumulativeProbability(lowerBound);

        // Print the result
        System.out.println("The probability of a pizza having a diameter within the range " +
                "[" + lowerBound + ", " + upperBound + "] is: " + probability);
    }
}