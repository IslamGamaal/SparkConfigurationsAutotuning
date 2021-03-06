package configurations;

import configurations.picker.BestConfigurationPicker;
import configurations.samples.ConfigurationsSampler;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

public class ConfigurationsHandlerTest {
    ConfigurationsHandler configurationsHandler;
    ConfigurationsSampler configurationsSampler;
    BestConfigurationPicker bestConfigurationPicker;
//    @Before
//    public void before(){
//        configurationsHandler  = mock(ConfigurationsHandlerImp.class);
//        configurationsSampler = mock(ConfigurationsSamplerImp.class);
//        bestConfigurationPicker = mock(BestConfigurationsPickerImp.class);
//
//        when(configurationsSampler.generateSamples(anyInt() , ArgumentMatchers.<Float>anyList() , ArgumentMatchers.<Float>anyList()))
//                .thenReturn(new ArrayList<List<Configuration>>());
//
//        when(bestConfigurationPicker.pickBestConfigurationsForApplication(ArgumentMatchers.<List<Configuration>>anyList() , any(SparkApplication.class)))
//                .thenReturn(new ArrayList<Configuration>());
//
//        List<Configuration> configurations = simulatePickingBestConf();
//        when(configurationsHandler.applyApplication(any(SparkApplication.class)))
//                .thenReturn(configurations);
//    }


//    @Test
//    public void returningBestConfigurationsForApplicationTest(){
//        Assert.assertEquals(new ArrayList<Configuration>() , configurationsHandler.applyApplication(new SparkApplication()));
//    }

//    private List<Configuration> simulatePickingBestConf() {
//        List<List<Configuration>> configurationsSamples = configurationsSampler.generateSamples(200 , new ArrayList<Float>() , new ArrayList<Float>());
//        return bestConfigurationPicker.pickBestConfigurationsForApplication(configurationsSamples,new SparkApplication());
//    }
}
