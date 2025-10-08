package pa.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends CrudRepository<Country, String> {
    Iterable<Country> findByName(String name);
    Iterable<Country> findByRegion(Region region);
    Iterable<Country> findByRegion_id(long id);
    // ...

    Iterable<Country> findByNameLikeIgnoreCase(String name);
    Iterable<Country> findAllByOrderByName();
    Iterable<Country> findAllByOrderByRegion();
    Iterable<Country> findAllByOrderByRegionAscNameAsc();

    Iterable<Country> findAllByOrderByRegion_nameAscNameAsc();

    @Query("select c from Country c join c.region r order by r.name, c.name")
    Iterable<Country> findAllByOrderByRegionNameCountryNameAlt();
}
