package pa.x1;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository(value="X1EmployeeRep")
public interface EmployeeRep extends CrudRepository<Employee, Long> {
}
