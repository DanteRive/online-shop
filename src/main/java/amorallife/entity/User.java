package amorallife.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends AbstractEntity{

    private String username;
    private String email;
    private String password;
    private boolean active;

    @Id
    @SequenceGenerator(name="users_s",sequenceName="users_s")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="users_s")
    @Column(nullable = false, name = "id")
    public Long getId() {
        return super.getId();
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "name", nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "active")
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
