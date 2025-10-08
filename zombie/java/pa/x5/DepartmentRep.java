package pa.x5;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("X5DepartmentRep")
public interface DepartmentRep extends CrudRepository<Department, Long> {
}
