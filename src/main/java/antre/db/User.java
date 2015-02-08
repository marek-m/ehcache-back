package antre.db;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String state;

    // ... additional members, often include @OneToMany mappings

    protected User() {
        // no-args constructor required by JPA spec
        // this one is protected since it shouldn't be used directly
    }

    public User(String name, String state) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getState() {
        return this.state;
    }

}
