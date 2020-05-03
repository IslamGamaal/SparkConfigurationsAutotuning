package spark;

import configurations.samples.ConfigurationsFilesHandler;
import configurations.samples.ConfigurationsFilesHandlerImp;
import configurations.utilites.Configuration;
import spark.historyserver.SparkHistoryServerHandler;
import spark.historyserver.SparkHistoryServerHandlerImp;
import spark.logs.SparkLogsHandler;
import spark.logs.SparkLogsHandlerImp;
import spark.submission.SparkSubmitter;
import spark.submission.SparkSubmitterImp;
import spark.utilites.SparkApplication;
import spark.utilites.SparkRunInfo;

import java.util.List;

public class SparkHandlerImp implements SparkHandler {
    private SparkSubmitter sparkSubmitter;
    private SparkLogsHandler sparkLogsHandler;
    private SparkHistoryServerHandler sparkHistoryServerHandler;
    public SparkHandlerImp(){
        this.sparkSubmitter = new SparkSubmitterImp();
        this.sparkLogsHandler = new SparkLogsHandlerImp();
        this.sparkHistoryServerHandler = new SparkHistoryServerHandlerImp();

    }
    public SparkApplication HandleApplication(List<Configuration> configurations, SparkApplication sparkApplication) {
        SparkRunInfo sparkRunInfo = new SparkRunInfo();
        ConfigurationsFilesHandler configurationsFilesHandler = new ConfigurationsFilesHandlerImp();
        sparkRunInfo.setConfigsFilePath(configurationsFilesHandler.writeConfigurationsInFile(configurations));
        sparkSubmitter.submitApplication(sparkApplication, sparkRunInfo);
        sparkApplication.setStagesJson(sparkHistoryServerHandler.getLatestAppStagesJson());
        sparkApplication.setOptimizedQueryPlan(sparkLogsHandler.getLatestAppOptimizedLogicalPlan());
        sparkApplication.setPhysicalPlan(sparkLogsHandler.getLatestAppPhysicalPlan());
        return sparkApplication;
    }

    public SparkApplication HandleApplication(SparkApplication sparkApplication) {
        SparkRunInfo sparkRunInfo = new SparkRunInfo();
        sparkSubmitter.submitApplication(sparkApplication, sparkRunInfo);
        sparkApplication.setStagesJson(sparkHistoryServerHandler.getLatestAppStagesJson());
        sparkApplication.setOptimizedQueryPlan(sparkLogsHandler.getLatestAppOptimizedLogicalPlan());
        sparkApplication.setPhysicalPlan(sparkLogsHandler.getLatestAppPhysicalPlan());
        return sparkApplication;
    }


}
