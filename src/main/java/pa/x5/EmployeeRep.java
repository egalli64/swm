package pa.x5;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository(value="X5EmployeeRep")
public interface EmployeeRep extends CrudRepository<Employee, Long> {
}
