package pa.x2;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("X2EmployeeRep")
public interface EmployeeRep extends CrudRepository<Employee, Long> {
}
