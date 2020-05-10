//package sampling;
//
//import configurations.samples.BinaryHyperCubeSampler;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//
//public class BLHSamplerTest {
//
//    @Test
//    public void smokeTest() {
//
//        List<Float> upperBounds = new ArrayList<>();
//        upperBounds.add(8.0f);
//        upperBounds.add(0.5f);
//        upperBounds.add(16.0f);
//        upperBounds.add(2.0f);
//        upperBounds.add(1.0f);
//
//        List<Float> lowerBounds = new ArrayList<>();
//        lowerBounds.add(1.0f);
//        lowerBounds.add(0.2f);
//        lowerBounds.add(2.0f);
//        lowerBounds.add(0.0f);
//        lowerBounds.add(0.0f);
//
//        BinaryHyperCubeSampler bLHSampler = new BinaryHyperCubeSampler();
//        List<List<Float>> samples = bLHSampler.generateSamples(10, upperBounds, lowerBounds);
//
//        assertEquals(samples.size(), 10);
//        for (List<Float> sample: samples) {
//            assertEquals(sample.size(), 5);
//            System.out.println(sample.toArray());
//        }
//
//    }
//    @Test(expected = RuntimeException.class)
//    public void emptyList1() {
//
//        List<Float> upperBounds = new ArrayList<>();
//
//        List<Float> lowerBounds = new ArrayList<>();
//        lowerBounds.add(1.0f);
//        lowerBounds.add(0.2f);
//        lowerBounds.add(2.0f);
//        lowerBounds.add(0.0f);
//        lowerBounds.add(0.0f);
//
//        BinaryHyperCubeSampler bLHSampler = new BinaryHyperCubeSampler();
//        List<List<Float>> samples = bLHSampler.generateSamples(10, upperBounds, lowerBounds);
//
//    }
//}