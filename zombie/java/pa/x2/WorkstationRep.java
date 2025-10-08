package pa.x2;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("X2WorkstationRep")
public interface WorkstationRep extends CrudRepository<Workstation, Long> {
}
