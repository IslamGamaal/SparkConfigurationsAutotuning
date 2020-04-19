package spark;

import configurations.utilites.Configuration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import spark.historyserver.SparkHistoryServerHandler;
import spark.historyserver.SparkHistoryServerHandlerImp;
import spark.logs.SparkLogsHandler;
import spark.logs.SparkLogsHandlerImp;
import spark.submission.SparkSubmitter;
import spark.submission.SparkSubmitterImp;
import spark.utilites.SparkApplication;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SparkHandlerTest {
    SparkHandler sparkHandler;
    SparkApplication sparkApplication;
    SparkSubmitter sparkSubmitter;
    SparkHistoryServerHandler sparkHistoryServerHandler;
    SparkLogsHandler sparkLogsHandler;
    @Before
    public void before(){
        sparkHandler = mock(SparkHandler.class);
        sparkApplication = mock(SparkApplication.class);
        sparkSubmitter = mock(SparkSubmitterImp.class);
        sparkHistoryServerHandler = mock(SparkHistoryServerHandlerImp.class);
        sparkLogsHandler = mock(SparkLogsHandlerImp.class);

        when(sparkHistoryServerHandler.getLatestAppStagesJson()).thenReturn("");

        when(sparkLogsHandler.getLatestAppOptimizedLogicalPlan()).thenReturn("");

        when(sparkLogsHandler.getLatestAppPhysicalPlan()).thenReturn("");
        String latestAppStagesJson = sparkHistoryServerHandler.getLatestAppStagesJson();
        String latestAppOptimizedLogicalPlan = sparkLogsHandler.getLatestAppOptimizedLogicalPlan();
        String latestAppPhysicalPlan = sparkLogsHandler.getLatestAppPhysicalPlan();

        when(sparkApplication.getStagesJson()).thenReturn(latestAppStagesJson);
        when(sparkApplication.getOptimizedQueryPlan()).thenReturn(latestAppOptimizedLogicalPlan);
        when(sparkApplication.getPhysicalPlan()).thenReturn(latestAppPhysicalPlan);

        when(sparkHandler.HandleApplication(ArgumentMatchers.<Configuration>anyList() , any(SparkApplication.class)))
                .thenReturn(new SparkApplication());

        when(sparkHandler.HandleApplication(any(SparkApplication.class))).thenReturn(new SparkApplication());
    }

    @Test
    public void runApplicationWithDefaultConfigurationsTest(){
        Assert.assertEquals("" , sparkHistoryServerHandler.getLatestAppStagesJson());
        Assert.assertEquals("" , sparkLogsHandler.getLatestAppOptimizedLogicalPlan());
        Assert.assertEquals("" , sparkLogsHandler.getLatestAppPhysicalPlan());

        SparkApplication sparkApplication = sparkHandler.HandleApplication(new SparkApplication());

        Assert.assertEquals("" , sparkApplication.getStagesJson());
        Assert.assertEquals("" , sparkApplication.getOptimizedQueryPlan());
        Assert.assertEquals("" , sparkApplication.getPhysicalPlan());

    }

    @Test
    public void runApplicationWithDiffConfigurations(){
        Assert.assertEquals("" , sparkHistoryServerHandler.getLatestAppStagesJson());
        Assert.assertEquals("" , sparkLogsHandler.getLatestAppOptimizedLogicalPlan());
        Assert.assertEquals("" , sparkLogsHandler.getLatestAppPhysicalPlan());

        SparkApplication sparkApplication = sparkHandler.HandleApplication(new ArrayList<Configuration>() , new SparkApplication());

        Assert.assertEquals("" , sparkApplication.getStagesJson());
        Assert.assertEquals("" , sparkApplication.getOptimizedQueryPlan());
        Assert.assertEquals("" , sparkApplication.getPhysicalPlan());

    }
}
