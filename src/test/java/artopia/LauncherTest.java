package artopia;

import org.junit.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * Created by wooferclaw on 26.03.2016.
 */
public class LauncherTest extends Assert{

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() throws Exception {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    public void checkMainConsoleOutput() throws Exception {
        Launcher launcher = new Launcher();
        launcher.run();
            assertEquals("Инициализация приложения ...", outContent.toString());
    }


}



