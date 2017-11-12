package testing;
import org.junit.*;

import static org.junit.Assert.*;

import validation.*;
public class MetricsTest {
    @Test
    public void gatherMetricsTest(){
        assertTrue(Metrics.getAllMetrics());
    }

    @Test
    public void startTest(){
        assertTrue(Metrics.start());
    }

    @Test
    public void stopTest(){
        assertTrue(Metrics.stop());
    }
}
