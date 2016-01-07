package artopia.entitiy;

import artopia.exception.EmptyPassword;
import artopia.exception.EmptyUsername;
import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.util.Date;

/**
 * @author Rottenwood
 */
@Entity
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", length = 32, nullable = false)
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "cyrillic_name", length = 32, nullable = false)
    private String cyrillicName;

    @Column(name = "cyrillic_name_2", length = 32, nullable = false)
    private String cyrillicName2;

    @Column(name = "cyrillic_name_3", length = 32, nullable = false)
    private String cyrillicName3;

    @Column(name = "cyrillic_name_4", length = 32, nullable = false)
    private String cyrillicName4;

    @Column(name = "cyrillic_name_5", length = 32, nullable = false)
    private String cyrillicName5;

    @Column(name = "cyrillic_name_6", length = 32, nullable = false)
    private String cyrillicName6;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_at")
    private Date createdAt = new Date();

    @Column(name = "room_id")
    private String roomId = "system-start";

    @Transient
    private boolean isAuthenticated = false;

    public User() {}

    public User(String name, String password) throws EmptyUsername, EmptyPassword
    {
        if (name.equals("")) {
            throw new EmptyUsername();
        } else if (password.equals("")) {
            throw new EmptyPassword();
        }

        String nameLowercase = name.toLowerCase();
        name = Character.toString(nameLowercase.charAt(0)).toUpperCase() + nameLowercase.substring(1);

        this.name = name;
        this.password = this.encryptPassword(password);
        this.cyrillicName = name;
        this.cyrillicName2 = name;
        this.cyrillicName3 = name;
        this.cyrillicName4 = name;
        this.cyrillicName5 = name;
        this.cyrillicName6 = name;
    }

    public boolean isAuthenticated()
    {
        return isAuthenticated;
    }

    public void authenticate()
    {
        isAuthenticated = true;
    }

    public String getName()
    {
        return name;
    }

    public long getId()
    {
        return id;
    }

    /**
     * @param plainPassword Пароль в незашифрованном виде
     * @return true если пароль верный
     */
    public boolean isPasswordValid(String plainPassword)
    {
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
