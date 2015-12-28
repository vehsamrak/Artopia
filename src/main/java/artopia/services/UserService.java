package artopia.services;

import artopia.exceptions.EmptyPassword;
import artopia.exceptions.EmptyUsername;
import artopia.models.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author Rottenwood
 */
public class UserService
{
    public static User login(String username, String password) throws EmptyPassword, EmptyUsername {
        Session session = DatabaseService.getSession();

        Query query = session.createQuery("FROM User WHERE username=:username");
        query.setParameter("username", username);
        User user = (User) query.uniqueResult();

        if (user == null) {
            user = new User(username, password);
            user.authenticate();

            Transaction transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
        }

        System.out.printf("[+] %s зашел в игру.%n", user.getUsername());

        return user;
    }
}
