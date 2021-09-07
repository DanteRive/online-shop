package amorallife.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role extends AbstractEntity {

    private String name;

    @Id
    @SequenceGenerator(name="roles_s",sequenceName="roles_s")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="roles_s")
    @Column(nullable = false, name = "id")
    public Long getId() {
        return super.getId();
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
