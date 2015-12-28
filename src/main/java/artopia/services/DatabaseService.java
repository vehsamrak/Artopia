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
    private static Session session = null;
    private static SessionFactory sessionFactory = null;

    private static SessionFactory createSessionFactory() {
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

    private static Session openSession() {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }

        return sessionFactory.openSession();
    }

    public static void close() {
        session.close();
    }

    public static Session getSession() throws SessionException {
        if (session == null) {
            session = openSession();
        }

        if (session == null) {
            throw new SessionException("Session was not opened.");
        }

        return session;
    }
}
