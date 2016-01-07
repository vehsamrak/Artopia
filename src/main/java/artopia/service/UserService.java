package artopia.service;

import artopia.entitiy.User;
import artopia.exception.EmptyPassword;
import artopia.exception.EmptyUsername;
import artopia.exception.WrongPassword;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author Rottenwood
 */
public class UserService
{
    private DatabaseService databaseService;

    public UserService(DatabaseService databaseService)
    {
        this.databaseService = databaseService;
    }

    public User login(String name, String password) throws EmptyPassword, EmptyUsername, WrongPassword
    {
        Session session = this.databaseService.getSession();

        Query query = session.createQuery("FROM User WHERE name=:name");
        query.setParameter("name", name);

        User user = (User) query.uniqueResult();

        if (user == null) {
            // TODO: 04.01.16 Нужно генерировать кириллическое имя для персонажа
            user = new User(name, password);
            user.authenticate();

            Transaction transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
        } else {
            if (!user.isPasswordValid(password)) {
                throw new WrongPassword();
            }
        }

        session.close();

        System.out.printf("[+] %s зашел в игру.%n", user.getName());

        return user;
    }
}
