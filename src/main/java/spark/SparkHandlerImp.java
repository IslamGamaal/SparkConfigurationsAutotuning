package spark;

import configurations.samples.ConfigurationsFilesHandler;
import configurations.samples.ConfigurationsFilesHandlerImp;
import configurations.utilites.Configuration;
import configurations.utilites.utils;
import org.moeaframework.problem.misc.Lis;
import spark.historyserver.SparkHistoryServerHandler;
import spark.historyserver.SparkHistoryServerHandlerImp;
import spark.logs.SparkLogsHandler;
import spark.logs.SparkLogsHandlerImp;
import spark.submission.SparkSubmitter;
import spark.submission.SparkSubmitterImp;
import spark.utilites.SparkApplication;
import spark.utilites.SparkRunInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SparkHandlerImp implements SparkHandler {
    private SparkSubmitter sparkSubmitter;
    private SparkLogsHandler sparkLogsHandler;
    private SparkHistoryServerHandler sparkHistoryServerHandler;
    private Map<String, Object> confsSpecs;
    public SparkHandlerImp(){
        this.sparkSubmitter = new SparkSubmitterImp();
        this.sparkLogsHandler = new SparkLogsHandlerImp();
        this.sparkHistoryServerHandler = new SparkHistoryServerHandlerImp();
        this.confsSpecs = new HashMap<>();
        utils.loadSpecs(confsSpecs);

    }
    public SparkApplication HandleApplication(List<Configuration> configurations, SparkApplication sparkApplication) {
        SparkRunInfo sparkRunInfo = new SparkRunInfo();
        ConfigurationsFilesHandler configurationsFilesHandler = new ConfigurationsFilesHandlerImp();
        sparkRunInfo.setConfigsFilePath(configurationsFilesHandler.writeConfigurationsInFile(configurations , (List<String>)confsSpecs.get("sparkhistory")));
        sparkRunInfo.setSparkDirectory((String) confsSpecs.get("sparkdirectory"));
        if (sparkSubmitter.submitApplication(sparkApplication, sparkRunInfo) == -1){
            return null;
        }
        sparkApplication.setStagesJson(sparkHistoryServerHandler.getLatestAppStagesJson());
        sparkApplication.setOptimizedQueryPlan(sparkLogsHandler.getLatestAppOptimizedLogicalPlan());
        sparkApplication.setPhysicalPlan(sparkLogsHandler.getLatestAppPhysicalPlan());
        return sparkApplication;
    }

    public SparkApplication HandleApplication(SparkApplication sparkApplication) {
        SparkRunInfo sparkRunInfo = new SparkRunInfo();
        if (sparkSubmitter.submitApplication(sparkApplication, sparkRunInfo) == -1){
            return null;
        }
        sparkApplication.setStagesJson(sparkHistoryServerHandler.getLatestAppStagesJson());
        sparkApplication.setOptimizedQueryPlan(sparkLogsHandler.getLatestAppOptimizedLogicalPlan());
        sparkApplication.setPhysicalPlan(sparkLogsHandler.getLatestAppPhysicalPlan());
        return sparkApplication;
    }


}
