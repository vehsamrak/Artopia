package artopia.service.locator;

import artopia.exception.ServiceNotFound;
import artopia.service.DatabaseService;
import artopia.service.UserService;
import artopia.service.command.CommandService;
import artopia.service.room.RoomRepository;

import java.util.ArrayList;

/**
 * @author Vehsamrak
 */
public class ServiceLocator

{
    final private ArrayList<AbstractService> serviceList = new ArrayList<>();

    public ServiceLocator()
    {
        DatabaseService databaseService = new DatabaseService();

        this.serviceList.add(databaseService);
        this.serviceList.add(new CommandService(this));
        this.serviceList.add(new UserService(databaseService));
        this.serviceList.add(new RoomRepository());
    }

    public AbstractService get(String serviceName) throws ServiceNotFound
    {
        for (AbstractService service : this.serviceList) {
            if (service.getName().equals(serviceName)) {
                return service;
            }
        }

        throw new ServiceNotFound();
    }
}
