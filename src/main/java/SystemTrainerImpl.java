import spark.utilites.SparkApplication;

import java.util.List;

public class SystemTrainerImpl implements SystemTrainer {
    public boolean instantiationTrigger(List<SparkApplication> applications) {
        return false;
    }
}
