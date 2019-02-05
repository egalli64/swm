package pa.x6;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("X6EmployeeRep")
public interface EmployeeRep extends CrudRepository<Employee, Long> {
}
