import spark.utilites.SparkApplication;

import java.util.List;

public interface SystemTrainer {
    boolean trainSystem(List<SparkApplication> applications);
}
