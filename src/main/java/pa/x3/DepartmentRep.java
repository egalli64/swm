package pa.x3;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("X3DepartmentRep")
public interface DepartmentRep extends CrudRepository<Department, Long> {
}
