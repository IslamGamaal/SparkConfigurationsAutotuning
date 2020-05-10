//package configurations.samples;
//
//import org.moeaframework.util.sequence.LatinHypercube;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class BinaryHyperCubeSampler implements ConfigurationsSampler {
//
//    @Override
//    public List<List<Float>> generateSamples(int numOfSamples, List<Float> upperBounds, List<Float> lowerBounds) {
//
//        if(upperBounds.isEmpty() || lowerBounds.isEmpty()) {
//            throw new RuntimeException("Binary Latin Hyper Cube Sampling: empty list");
//        } else if (upperBounds.size() != lowerBounds.size()) {
//            throw new RuntimeException("Binary Latin Hyper Cube Sampling: incompatible bounds sizes");
//        }
//
//        int configSize = upperBounds.size();
//
//        LatinHypercube latinHypercube = new LatinHypercube();
//        double[][] lHSamples = latinHypercube.generate(numOfSamples, configSize);
//
//        List<List<Float>> bLHSamples = new ArrayList<>();
//
//        for (int i = 0; i < numOfSamples; i++) {
//            List<Float> sample = new ArrayList<>();
//            for (int j = 0; j < configSize; j++) {
//                double val = lowerBounds.get(j) + (lHSamples[i][j] * (upperBounds.get(j) - lowerBounds.get(j)));
//                sample.add((float) val);
//            }
//            bLHSamples.add(sample);
//        }
//
//        return bLHSamples;
//    }
//}
