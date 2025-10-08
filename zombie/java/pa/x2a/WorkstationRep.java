package pa.x2a;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("X2aWorkstationRep")
public interface WorkstationRep extends CrudRepository<Workstation, Long> {
}
