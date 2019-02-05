package pa.x6;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository(value="X6EmployeeRep")
public interface EmployeeRep extends CrudRepository<Employee, Long> {
}
