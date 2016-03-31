import artopia.Launcher;
import artopia.service.locator.ServiceLocator;

/**
 * @author Rottenwood
 */
public class Main {
    public static void main(String[] args) {
        Launcher launcher = new Launcher();
        launcher.setServiceLocator(new ServiceLocator()).run();
    }
}
