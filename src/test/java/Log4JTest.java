import org.junit.Test;
import org.junit.runner.RunWith;
import pers.common.LogAspect;

public class Log4JTest {
    @Test
    public void testLog4JConfiguration(){
        new LogAspect().log();
    }
}
