package spark.historyserver;

import org.junit.Test;
import spark.historyserver.model.Environment;
import spark.historyserver.model.Executor;

public class SparkHistoryServerHandlerTest {

    private SparkHistoryServerHandlerImp handler = new SparkHistoryServerHandlerImp();

    @Test
    public void test() {
        Long duration = handler.getLatestAppDuration();
        System.out.println(duration);
    }
}
