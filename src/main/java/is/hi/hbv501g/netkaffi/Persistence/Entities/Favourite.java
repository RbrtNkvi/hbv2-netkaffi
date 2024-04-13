package is.hi.hbv501g.netkaffi.Persistence.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name="favourites")
@IdClass(FavouriteId.class)
public class Favourite {
    @Id
    private String username;
    @Id
    private String productName;

    public Favourite(){

    }

    public Favourite(String username, String productName){
        this.username = username;
        this.productName = productName;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getProductName(){
        return productName;
    }

    public void setProductName(String productName){
        this.productName = productName;
    }
}
