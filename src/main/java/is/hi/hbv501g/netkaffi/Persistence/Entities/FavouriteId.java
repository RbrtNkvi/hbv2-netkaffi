package is.hi.hbv501g.netkaffi.Persistence.Entities;

import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

public class FavouriteId implements Serializable {

    private String username;
    private String productName;

    public FavouriteId(){

    }

    public FavouriteId(String username, String productName){
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavouriteId favId = (FavouriteId) o;
        return username.equals(favId.getUsername()) && productName.equals(favId.getProductName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, productName);
    }
}
