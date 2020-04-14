import org.junit.Test;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class InitializationHandlerTest {

    @Test
    public void testInitialization() {
        InitializationHandler initializationHandlerMock = mock(InitializationHandlerImpl.class);
        doReturn(true).when(initializationHandlerMock).instantiationTrigger("table1 table2 table3");


    }
}
