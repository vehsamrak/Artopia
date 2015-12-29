package artopia.services;

import artopia.exceptions.EmptyPassword;
import artopia.exceptions.EmptyUsername;
import artopia.exceptions.WrongPassword;
import artopia.models.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author Rottenwood
 */
public class UserService
{
    private DatabaseService databaseService;

    public UserService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public User login(String username, String password) throws EmptyPassword, EmptyUsername, WrongPassword {
        Session session = this.databaseService.getSession();

        Query query = session.createQuery("FROM User WHERE username=:username");
        query.setParameter("username", username);
        User user = (User) query.uniqueResult();

        if (user == null) {
            user = new User(username, password);
            user.authenticate();

            Transaction transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
        } else {
            if (!password.equals(user.getPassword())) {
                throw new WrongPassword();
            }
        }

        System.out.printf("[+] %s зашел в игру.%n", user.getUsername());

        return user;
    }
}
