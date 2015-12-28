package artopia.services;

import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.jetbrains.annotations.Nullable;

/**
 * @author Vehsamrak
 */
public class DatabaseService
{
    private static Session session = null;

    @Nullable
    private static Session openSession() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try {
            SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

            return sessionFactory.openSession();
        } catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);
        }

        return null;
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
