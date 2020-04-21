package spark.historyserver.report;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.google.common.io.Files;


public class Report {
    private StringBuilder builder = new StringBuilder(); 
    
    public void addRow(Summary summary) {
        
        BiFunction<String, Object, String> getPropertyOrDefault = (property, defaultValue) -> {
            String value = summary.getSparkProperties().remove(property);
            return value==null ? (defaultValue == null ? "" : String.valueOf(defaultValue)) : value;
        };
        Function<String, String> getProperty = (property) -> getPropertyOrDefault.apply(property, null);
        
        Object[][] columns = new Object[][] {
            
            { "Name", summary.getApplicationName() },
            
            { "[num-executors]", summary.getExecutorsCount() },
            { "[spark.cores.max]", getProperty.apply("spark.cores.max") },
            { "[spark.driver.cores]", getProperty.apply("spark.driver.cores") },
            { "[spark.driver.memory]", getProperty.apply("spark.driver.memory") },
            { "[spark.executor.cores]", getPropertyOrDefault.apply("spark.executor.cores", summary.getCoresPerExecutor()) },
            { "[spark.executor.memory]", getProperty.apply("spark.executor.memory") },
            { "[spark.sql.shuffle.partitions]", getProperty.apply("spark.sql.shuffle.partitions") },
            { "[spark.cores.max]", getProperty.apply("spark.cores.max") },
            { "[spark.default.parallelism]", getProperty.apply("spark.default.parallelism") },
            { "[spark.sql.shuffle.partitions]", getProperty.apply("sql.shuffle.partitions") },
            { "[spark.shuffle.compress]", getProperty.apply("spark.shuffle.compress") },
            { "[spark.shuffle.spill.compress]", getProperty.apply("shuffle.spill.compress") },
            { "[spark.shuffle.file.buffer]", getProperty.apply("shuffle.file.buffer") },
            { "[spark.broadcast.blockSize]", getProperty.apply("spark.broadcast.blockSize") },
            { "[spark.memory.fraction]", getProperty.apply("spark.memory.fraction") },
            { "[spark.memory.storageFraction]", getProperty.apply("spark.memory.storageFraction") },
            { "[spark.reducer.maxSizeInFlight]", getProperty.apply("spark.reducer.maxSizeInFlight") },
            { "[spark.locality.wait]", getProperty.apply("spark.locality.wait") },
            
            { "Duration", summary.getDuration() },
            { "Attempts", summary.getAttemptsCount() },
            { "Jobs", summary.getJobsCount() },
            { "Tasks", summary.getTasksCount() },
            { "Tasks Per Job", summary.getTasksPerJob() },
            { "Stages", summary.getStagesCount() },
            
            { "Stages Input (Bytes)", summary.getStagesInputBytes() },
            { "Stages Input (Records)", summary.getStagesInputRecords() },
            { "Stages Output (Bytes)", summary.getStagesOutputBytes() },
            { "Stages Output (Records)", summary.getStagesOutputRecords() },
            { "Stages Shuffle Read (Bytes)", summary.getStagesShuffleReadBytes() },
            { "Stages Shuffle Read (Records)", summary.getStagesShuffleReadRecords() },
            { "Stages Shuffle Write (Bytes)", summary.getStagesShuffleWriteBytes() },
            { "Stages Shuffle Write (Records)", summary.getStagesShuffleWriteRecords() },
            { "Stages Memory Spill (Bytes)", summary.getStagesMemorySpill() },
            { "Stages Disk Spill (Bytes)", summary.getStagesDiskSpill() },

            { "Executor Max Memory", summary.getMaxMemoryPerExecutor() },
            { "Executors Processing Time", summary.getExecutorsProcessingTime() },
            { "Executors GC Time", summary.getExecutorsGCTime() },
            { "Executors Input (Bytes)", summary.getExecutorsInputBytes() },
            { "Executors Shuffle Read (Bytes)", summary.getExecutorsShuffleReadBytes() },
            { "Executors Shuffle Write (Bytes)", summary.getExecutorsShuffleWriteBytes() },
            { "Executors Failed Tasks", summary.getExecutorsFailedTasks() },
        };
        Consumer<Function<Object[], String>> row =
                (a) -> builder.append(Arrays.stream(columns).map(a).collect(Collectors.joining(",")));
        if (builder.length() == 0) row.accept(c -> String.valueOf(c[0]));
        builder.append("\n");
        row.accept(c -> String.valueOf(c[1]));
    }
    
    public void save(String path) throws IOException {
        Files.write(builder.toString().getBytes(), new File(path));
    }
}
