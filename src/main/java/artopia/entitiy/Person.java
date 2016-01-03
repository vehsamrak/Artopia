package artopia.entitiy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * @author Vehsamrak
 */
@Entity
@Table(name = "characters")
public class Person

{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 32, nullable = false)
    private String name;

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

    @Column(name = "user_id")
    private User user;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_at")
    private Date createdAt = new Date();

    public Person() {}
}
