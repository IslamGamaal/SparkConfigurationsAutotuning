import configurations.utilites.Configuration;
import spark.utilites.SparkApplication;

import java.util.List;

public interface BestConfigurationsPredictor {
    List<Configuration> predictBestConfigurations(SparkApplication sparkApplication);
}
