package pa.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends CrudRepository<Country, String> {
	@Override
	List<Country> findAll();

	List<Country> findByName(String name);
	List<Country> findByRegion(Region region);
	List<Country> findByRegion_id(long id);

	List<Country> findByNameLikeIgnoreCase(String name);
	List<Country> findAllByOrderByName();
    List<Country> findAllByOrderByRegion();
    List<Country> findAllByOrderByRegionAscNameAsc();

    List<Country> findAllByOrderByRegion_nameAscNameAsc();
}
