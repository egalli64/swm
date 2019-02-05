package pa.x6a;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository(value="X6aEmployeeRep")
public interface EmployeeRep extends CrudRepository<Employee, Long> {
}
