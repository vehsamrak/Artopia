package artopia.service;

import artopia.service.locator.AbstractService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * @author Vehsamrak
 */
public class DatabaseService extends AbstractService
{
    private SessionFactory sessionFactory = null;

    public DatabaseService()
    {
        this.sessionFactory = createSessionFactory();
    }

    private SessionFactory createSessionFactory()
    {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try {
            return new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);
            throw e;
        }
    }

    public Session openSession()
    {
        return this.sessionFactory.openSession();
    }

    @Override
    public String getName()
    {
        return "DatabaseService";
    }
}
