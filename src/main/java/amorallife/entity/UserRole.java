package amorallife.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_roles")
public class UserRole extends AbstractEntity {

    private User user;
    private Role role;

    @Id
    @SequenceGenerator(name="user_roles_s",sequenceName="user_roles_s")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="user_roles_s")
    @Column(nullable = false, name = "id")
    public Long getId() {
        return super.getId();
    }

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
