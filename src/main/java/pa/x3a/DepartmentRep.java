package pa.x3a;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository(value="X3aWorkstationRep")
public interface DepartmentRep extends CrudRepository<Department, Long> {
}
