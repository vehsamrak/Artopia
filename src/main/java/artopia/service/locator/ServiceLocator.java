package artopia.service.locator;

import artopia.exception.ServiceNotFound;
import artopia.service.DatabaseService;
import artopia.service.UserService;
import artopia.service.command.CommandService;
import artopia.service.room.RoomParser;
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
        this.serviceList.add(new RoomRepository(new RoomParser()));
    }

    public AbstractService get(Service serviceName) throws ServiceNotFound
    {
        for (AbstractService service : this.serviceList) {
            if (service.getName() == serviceName) {
                return service;
            }
        }

        throw new ServiceNotFound();
    }
}
