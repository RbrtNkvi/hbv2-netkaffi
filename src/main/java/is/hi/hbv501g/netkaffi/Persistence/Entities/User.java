package is.hi.hbv501g.netkaffi.Persistence.Entities;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name="users")
public class User {

    @Id
    private String username;
    private String password;
    private Boolean isadmin;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "fav_product",
            joinColumns = @JoinColumn(name = "user_name"),
            inverseJoinColumns = @JoinColumn(name = "product_name"))
    private Set<Product> favourites = new HashSet<>();

    public User(String name, String password, Boolean isAdmin){
        this.username = name;
        this.password = password;
        this.isadmin = isAdmin;
    }

    public User(String name, String password){
        this.username = name;
        this.password = password;
    }

    public User() {

    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public Boolean getIsAdmin(){
        return isadmin;
    }

    public void setIsAdmin(boolean isAdmin){
        this.isadmin = isAdmin;
    }

    /*
    public List<Booking> getBookings() {
        return bookings;
    }
    */

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public void addFavourite(Product product){
        this.favourites.add(product);
        product.getUsers().add(this);
    }

    public void removeFavourite(String productName){
        Product p = this.favourites.stream().filter(t -> Objects.equals(t.getName(), productName)).findFirst().orElse(null);
        if (p != null) {
            this.favourites.remove(p);
            p.getUsers().remove(this);
        }
    }
}
