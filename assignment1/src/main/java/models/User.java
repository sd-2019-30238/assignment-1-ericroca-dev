package models;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Observable;
import java.util.Observer;

@Entity
@Table(name = "users")
public class User implements Observer {

    @Transient
    private Order order;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NaturalId
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public void update(Observable observable, Object object) {
        order = (Order) observable;
        System.out.println("Observer: " + order.getStatus());
    }
}
