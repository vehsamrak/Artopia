import artopia.Launcher;
import artopia.service.locator.ServiceLocator;

/**
 * @author Rottenwood
 */
public class Main
{
    public static void main(String[] args)
    {
        new Launcher(new ServiceLocator()).run();
    }
}
