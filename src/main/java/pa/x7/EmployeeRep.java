package pa.x7;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("X7EmployeeRep")
public interface EmployeeRep extends CrudRepository<Employee, Long> {
}
