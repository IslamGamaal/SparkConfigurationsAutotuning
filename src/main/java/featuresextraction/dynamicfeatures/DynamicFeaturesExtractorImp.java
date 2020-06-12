package featuresextraction.dynamicfeatures;

import featuresextraction.utilities.Feature;
import featuresextraction.utilities.SupportedFeatures;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DynamicFeaturesExtractorImp implements DynamicFeaturesExtractor {
    final int ONE_MILLION = 1000000;
    public List<Feature> extract(String stagesJson) {

        List<Feature> dynamicFeatures = new ArrayList<>();
        JSONArray stages = new JSONArray(stagesJson);

        double executorRunTime = 0;
        double executorCPUTime = 0;
        double inputBytes = 0;
        int inputRecordsCount = 0;
        double outputBytes = 0;
        int outputRecordsCount = 0;
        double shuffleReadBytes = 0;
        int shuffleReadRecords = 0;
        double shuffleWriteBytes = 0;
        int shuffleWriteRecords = 0;
        double memoryBytesSpilled = 0;
        double diskBytesSpilled = 0;
        int rddsAffectedCount = 0;
        double cpuTimeRatio;
        double memorySpillingRatio;
        double diskSpillingRatio;

        for(int i = 0; i < stages.length(); i++) {
            JSONObject currentStage = stages.getJSONObject(i);
            executorRunTime += currentStage.getDouble("executorRunTime");
            executorCPUTime += currentStage.getDouble("executorCpuTime");
            inputBytes += currentStage.getDouble("inputBytes");
            inputRecordsCount += currentStage.getLong("inputRecords");
            outputBytes += currentStage.getDouble("outputBytes");
            outputRecordsCount += currentStage.getLong("outputRecords");
            shuffleReadBytes += currentStage.getDouble("shuffleReadBytes");
            shuffleReadRecords += currentStage.getLong("shuffleReadRecords");
            shuffleWriteBytes += currentStage.getDouble("shuffleWriteBytes");
            shuffleWriteRecords += currentStage.getLong("shuffleWriteRecords");
            memoryBytesSpilled += currentStage.getDouble("memoryBytesSpilled");
            diskBytesSpilled += currentStage.getDouble("diskBytesSpilled");
            rddsAffectedCount += currentStage.getJSONArray("rddIds").length();
        }
        executorCPUTime /= ONE_MILLION; //convert to milliseconds.

        cpuTimeRatio = executorCPUTime / executorRunTime;
        if (memoryBytesSpilled + diskBytesSpilled == 0) memorySpillingRatio = 0;
        else memorySpillingRatio = memoryBytesSpilled / (memoryBytesSpilled + diskBytesSpilled);
        diskSpillingRatio = 1 - memorySpillingRatio;

        dynamicFeatures.add(new Feature(SupportedFeatures.NUM_OF_STAGES, String.valueOf(stages.length()) , "string"));
        dynamicFeatures.add(new Feature(SupportedFeatures.EXECUTOR_RUN_TIME, String.valueOf(executorRunTime), "string"));
        dynamicFeatures.add(new Feature(SupportedFeatures.EXECUTOR_CPU_TIME, String.valueOf(executorCPUTime), "string"));
        dynamicFeatures.add(new Feature(SupportedFeatures.INPUT_BYTES, String.valueOf(inputBytes), "string"));
        dynamicFeatures.add(new Feature(SupportedFeatures.INPUT_RECORDS_COUNT, String.valueOf(inputRecordsCount), "string"));
        dynamicFeatures.add(new Feature(SupportedFeatures.OUTPUT_BYTES, String.valueOf(outputBytes),"string"));
        dynamicFeatures.add(new Feature(SupportedFeatures.OUTPUT_RECORDS_COUNT, String.valueOf(outputRecordsCount),"string"));
        dynamicFeatures.add(new Feature(SupportedFeatures.SHUFFLE_READ_BYTES, String.valueOf(shuffleReadBytes),"string"));
        dynamicFeatures.add(new Feature(SupportedFeatures.SHUFFLE_READ_RECORDS, String.valueOf(shuffleReadRecords),"string"));
        dynamicFeatures.add(new Feature(SupportedFeatures.SHUFFLE_WRITE_BYTES, String.valueOf(shuffleWriteBytes),"string"));
        dynamicFeatures.add(new Feature(SupportedFeatures.SHUFFLE_WRITE_RECORDS, String.valueOf(shuffleWriteRecords),"string"));
        dynamicFeatures.add(new Feature(SupportedFeatures.MEMORY_BYTES_SPILLED, String.valueOf(memoryBytesSpilled),"string"));
        dynamicFeatures.add(new Feature(SupportedFeatures.DISK_BYTES_SPILLED, String.valueOf(diskBytesSpilled),"string"));
        dynamicFeatures.add(new Feature(SupportedFeatures.RDDS_AFFECTED_COUNT, String.valueOf(rddsAffectedCount),"string"));
        dynamicFeatures.add(new Feature(SupportedFeatures.CPU_TIME_RATIO, String.valueOf(cpuTimeRatio),"string"));
        dynamicFeatures.add(new Feature(SupportedFeatures.MEMORY_SPILLING_RATIO, String.valueOf(memorySpillingRatio),"string"));
        dynamicFeatures.add(new Feature(SupportedFeatures.DISK_SPILLING_RATIO, String.valueOf(diskSpillingRatio),"string"));

        return dynamicFeatures;
    }
}
