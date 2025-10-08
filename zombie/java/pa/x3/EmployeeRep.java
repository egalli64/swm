package pa.x3;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("X3EmployeeRep")
public interface EmployeeRep extends CrudRepository<Employee, Long> {
}
