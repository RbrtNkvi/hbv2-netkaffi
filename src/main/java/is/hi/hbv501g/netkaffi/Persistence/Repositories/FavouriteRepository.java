package is.hi.hbv501g.netkaffi.Persistence.Repositories;

import is.hi.hbv501g.netkaffi.Persistence.Entities.Favourite;
import is.hi.hbv501g.netkaffi.Persistence.Entities.FavouriteId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavouriteRepository extends JpaRepository<Favourite, FavouriteId> {

    List<Favourite> findAllByUsername(String username);
    Favourite save(Favourite favourite);
    void delete(Favourite favourite);

}
