import classification.Classifier;
import classification.ClassifierImp;
import configurations.ConfigurationsHandler;
import configurations.ConfigurationsHandlerImp;
import configurations.picker.BestConfigurationPicker;
import configurations.picker.BestConfigurationsPickerImp;
import featuresextraction.FeaturesExtractionHandler;
import featuresextraction.FeaturesExtractionHandlerImp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import spark.utilites.SparkApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SystemTrainerTest {
    SystemTrainer systemTrainer;
    FeaturesExtractionHandler featuresExtractionHandler;
    Classifier classifier;
    ConfigurationsHandler configurationsHandler;
    BestConfigurationPicker bestConfigurationPicker;
    List<String> configurations;
    Boolean success;

    @Before
    public void before() {
        systemTrainer = mock(SystemTrainerImpl.class);
        featuresExtractionHandler = mock(FeaturesExtractionHandlerImp.class);
        configurationsHandler = mock(ConfigurationsHandlerImp.class);
        bestConfigurationPicker = mock(BestConfigurationsPickerImp.class);
        classifier = mock(ClassifierImp.class);
    }


    @Test
    public void InstantiationTriggerTest() {
        HashMap<List, List> model = new HashMap();
        //todo fix this thing 3ashan 3amelha ay 7aga mo2akatan,,
        success = classifier.trainModel(bestConfigurationPicker.pickBestConfigurationsForApplication(configurationsHandler.applyApplication(new SparkApplication()) , new SparkApplication()));
        when(systemTrainer.instantiationTrigger(new ArrayList<SparkApplication>()))
                .thenReturn(success);

        Assert.assertEquals(true, systemTrainer.instantiationTrigger(new ArrayList<SparkApplication>()));
    }
}
