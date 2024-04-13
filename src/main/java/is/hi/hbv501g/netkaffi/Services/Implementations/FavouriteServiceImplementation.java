package is.hi.hbv501g.netkaffi.Services.Implementations;

import is.hi.hbv501g.netkaffi.Persistence.Entities.Favourite;
import is.hi.hbv501g.netkaffi.Persistence.Repositories.BookingRepository;
import is.hi.hbv501g.netkaffi.Persistence.Repositories.FavouriteRepository;
import is.hi.hbv501g.netkaffi.Services.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavouriteServiceImplementation implements FavouriteService {

    private FavouriteRepository favouriteRepository;

    @Autowired
    public FavouriteServiceImplementation(FavouriteRepository favouriteRepository){
        this.favouriteRepository = favouriteRepository;
    }

    public List<Favourite> findAllByUsername(String username){
        return favouriteRepository.findAllByUsername(username);
    }

    public Favourite save(Favourite favourite){
        return favouriteRepository.save(favourite);
    }

    public void delete(Favourite favourite){
        favouriteRepository.delete(favourite);
    }
}
