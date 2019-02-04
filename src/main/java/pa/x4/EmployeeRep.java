package pa.x4;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository(value="X4EmployeeRep")
public interface EmployeeRep extends CrudRepository<Employee, Long> {
}
