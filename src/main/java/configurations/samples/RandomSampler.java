package configurations.samples;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomSampler implements ConfigurationsSampler {

    public List<List<Float>> generateSamples(int numOfSamples, List<Float> upperbounds, List<Float> lowerbounds) {

        if(upperbounds.size() != lowerbounds.size()) {
            throw new RuntimeException("upper bound and lower bound lists not in the same size");
        }

        List samples = new ArrayList();
        samples.add(upperbounds);
        samples.add(lowerbounds);

        int sampleSize = upperbounds.size();
        Random rand = new Random(System.currentTimeMillis());

        while(samples.size() < numOfSamples) {
            List<Float> sample = new ArrayList<>();
            for (int i = 0; i < sampleSize; i++) {
                float upperbound = upperbounds.get(i);
                float lowerbound = lowerbounds.get(i);
                float val = rand.nextFloat() * (upperbound - lowerbound) + lowerbound;
                sample.add(val);
            }
            samples.add(sample);
        }

        return samples;

    }

}
