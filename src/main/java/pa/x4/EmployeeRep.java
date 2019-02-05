package pa.x4;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("X4EmployeeRep")
public interface EmployeeRep extends CrudRepository<Employee, Long> {
}
