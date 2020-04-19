import configurations.utilites.Configuration;
import spark.utilites.SparkApplication;

import java.util.List;

public interface SingleApplicationHandler {
    List<Configuration> predictSuitableConfigurations(SparkApplication sparkApplication);
}
