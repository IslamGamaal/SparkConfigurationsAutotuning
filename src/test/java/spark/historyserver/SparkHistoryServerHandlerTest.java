package spark.historyserver;

import org.junit.Test;
import spark.historyserver.model.Environment;
import spark.historyserver.model.Executor;
import spark.historyserver.model.ExecutorList;

public class SparkHistoryServerHandlerTest {

    private SparkHistoryServerHandlerImp handler = new SparkHistoryServerHandlerImp();

    @Test
    public void test() {
        Executor[] env = handler.getLatestAppExecutorSettings();
        System.out.println();
    }
}
