package artopia.handler;

import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;

/**
 * @author Vehsamrak
 */
public class ExceptionHandlerTest extends Assert
{

    @Test
    public void ExceptionHandler_constructorCalled_ReturnsNotNull() throws Exception
    {
        ExceptionHandler exceptionHandler = new ExceptionHandler();

        assertNotNull(exceptionHandler);
    }

    @Test
    public void testHandle()
    {
        assertTrue(ExceptionHandler.handle(mock(RuntimeException.class)));
    }
}