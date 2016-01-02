package artopia.models;

import artopia.exceptions.EmptyPassword;
import artopia.exceptions.EmptyUsername;
import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.*;

/**
 * @author Rottenwood
 */
@Entity
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Transient
    private boolean isAuthenticated = false;

    public User() {}

    // TODO: 22.12.15 Обработка пароля
    public User(String username, String password) throws EmptyUsername, EmptyPassword {
        if (username.equals("")) {
            throw new EmptyUsername();
        } else if (password.equals("")) {
            throw new EmptyPassword();
        }

        String usernameInLowercase = username.toLowerCase();
        username = Character.toString(usernameInLowercase.charAt(0)).toUpperCase() + usernameInLowercase.substring(1);

        this.username = username;
        this.password = this.encryptPassword(password);
    }


    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void authenticate() {
        isAuthenticated = true;
    }

    public String getUsername() {
        return username;
    }

    public long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    /**
     * @param plainPassword Пароль в незашифрованном виде
     * @return true если пароль верный
     */
    public boolean isPasswordValid(String plainPassword) {
        return this.encryptPassword(plainPassword).equals(this.password);
    }

    /**
     * @param password Пароль
     * @return Зашифрованная средствами MD5 строка пароля
     */
    private String encryptPassword(String password)
    {
        return DigestUtils.md5Hex(password);
    }
}
