package pa.x2;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository(value="X2WorkstationRep")
public interface WorkstationRep extends CrudRepository<Workstation, Long> {
}
