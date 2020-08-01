package hw3.Entities;

import javax.persistence.*;

@Entity
@Table(name = "lot")
public class Lot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name = "current_bet")
    private Integer current_bet;

    @OneToOne
    @JoinColumn(name = "users")
    private User user;

    @Version
    long version;

    @Override
    public String toString(){
        return "id: "+id+", Name: "+name+", Current bet: "+current_bet+", User: "+user;
    }

    public Lot(String name, Integer current_bet, User user) {
        this.name = name;
        this.current_bet = current_bet;
        this.user = user;
    }

    public Lot() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getCurrent_bet() {
        return current_bet;
    }

    public User getUser() {
        return user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCurrent_bet(Integer current_bet) {
        this.current_bet = current_bet;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getVersion() {
        return version;
    }
}
