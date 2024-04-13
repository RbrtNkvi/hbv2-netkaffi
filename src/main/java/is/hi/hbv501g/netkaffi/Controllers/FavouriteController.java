package is.hi.hbv501g.netkaffi.Controllers;

import is.hi.hbv501g.netkaffi.Persistence.Entities.Favourite;
import is.hi.hbv501g.netkaffi.Services.FavouriteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FavouriteController {

    FavouriteService favouriteService;

    public FavouriteController(FavouriteService favouriteService){
        this.favouriteService = favouriteService;
    }

    @GetMapping("/favourite/{username}")
    public List<Favourite> getFavourites(@PathVariable String username){
        return favouriteService.findAllByUsername(username);
    }

    @PostMapping("/favourite/{username}/{productName}")
    public Favourite addFavourite(@PathVariable("username") String username, @PathVariable("productName") String productName){
        Favourite favourite = new Favourite(username,productName);
        return favouriteService.save(favourite);
    }

    @PostMapping(value="/favourite",consumes="application/json",produces="application/json")
    public void deleteFavourite(@RequestBody Favourite favourite){
        favouriteService.delete(favourite);
    }
}
