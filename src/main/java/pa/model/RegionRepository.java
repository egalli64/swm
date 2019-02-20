package pa.model;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends CrudRepository<Region, Long> {
    @Query("select r from Region r")
    List<Region> myFindAll();

    @Query("select r.name from Region r")
    List<String> myFindAllNames();

    @Query("select r from Region r where r.name like 'A%'")
    List<String> myStartingByA();
}
