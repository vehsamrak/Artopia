package artopia.services;

import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * @author Vehsamrak
 */
public class DatabaseService

{
    private Session session = null;
    private SessionFactory sessionFactory = null;

    public DatabaseService() {
        this.openSession();
    }

    private SessionFactory createSessionFactory() {
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

    public Session openSession() {
        if (this.sessionFactory == null) {
            this.sessionFactory = createSessionFactory();
        }

        return this.sessionFactory.openSession();
    }

    public void close() {
        this.session.close();
    }

    public Session getSession() throws SessionException {
        if (this.session == null) {
            this.session = openSession();
        }

        if (this.session == null) {
            throw new SessionException("Session was not opened.");
        }

        return this.session;
    }
}
