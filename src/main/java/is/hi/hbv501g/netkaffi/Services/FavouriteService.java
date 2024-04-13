package is.hi.hbv501g.netkaffi.Services;

import is.hi.hbv501g.netkaffi.Persistence.Entities.Favourite;

import java.util.List;

public interface FavouriteService {

    List<Favourite> findAllByUsername(String username);
    Favourite save(Favourite favourite);
    void delete(Favourite favourite);
}
