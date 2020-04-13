package spark.submission;

import java.util.List;

public interface SparkSubmitter {

    /**
     *
     * @param configurations List<String> : Containing the configurations to be used in application submission.
     */
    void submitApplication(List<String> configurations);
}
