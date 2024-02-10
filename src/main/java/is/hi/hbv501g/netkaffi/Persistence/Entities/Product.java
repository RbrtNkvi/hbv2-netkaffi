package is.hi.hbv501g.netkaffi.Persistence.Entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="products")
public class Product {
    @Id
    private String name;

    private String type;
    private int price;
    private boolean deleted;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings = new ArrayList<>();

    @ManyToMany(mappedBy = "favourites")
    private Set<User> users = new HashSet<>();

    public Product(){

    }
    public Product(String name, String type, int price){
        this.name = name;
        this.type = type;
        this.price = price;
        this.deleted = false;
    }

    public Product(String name, String type, String price){
        this.name = name;
        this.type = type;
        this.price = Integer.parseInt(price);
        this.deleted = false;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public Set<User> getUsers(){
        return users;
    }

    public void setUsers(Set<User> users){
        this.users = users;
    }
}
