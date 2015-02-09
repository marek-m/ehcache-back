package antre.db;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="User_SEQ", sequenceName="User_SEQ", initialValue=1, allocationSize=1)
public class User1 implements Serializable {

	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public User1() {
		super();
	}

	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="User_SEQ")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String state;

    // ... additional members, often include @OneToMany mappings

    public User1(String name, String state) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getState() {
        return this.state;
    }

}
