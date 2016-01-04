package artopia.service;

import artopia.entitiy.Person;
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

    public User login(String username, String password) throws EmptyPassword, EmptyUsername, WrongPassword
    {
        Session session = this.databaseService.getSession();

        Query query = session.createQuery("FROM User WHERE username=:username");
        query.setParameter("username", username);

        User user = (User) query.uniqueResult();

        if (user == null) {
            user = new User(username, password);
            user.authenticate();

            // TODO: 04.01.16 Нужно генерировать кириллическое имя для персонажа
            Person person = new Person(username, user);

            Transaction transaction = session.beginTransaction();
            session.persist(user);
            session.persist(person);
            transaction.commit();
        } else {
            if (!user.isPasswordValid(password)) {
                throw new WrongPassword();
            }
        }

        System.out.printf("[+] %s зашел в игру.%n", user.getUsername());

        return user;
    }
}
