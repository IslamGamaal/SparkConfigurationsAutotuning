import spark.utilites.SparkApplication;

import java.util.List;

public interface SingleApplicationHandler {
    List<String> predictSuitableConfigurations(SparkApplication sparkApplication);
}
