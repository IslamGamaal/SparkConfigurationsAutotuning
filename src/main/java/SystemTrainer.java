import spark.utilites.SparkApplication;

import java.util.List;

public interface SystemTrainer {
    //todo Sarah etsarafi w put the dataset as parameter here to be used in mocking (ma3rafsh hateb2a
    // 3amla ezay bas heya 3obara 3an two lists ma3mollohom merge per tupel
    // elRow el wa7ed feeh el features + el configurations )
    boolean instantiationTrigger(List<SparkApplication> applications);
}
