package pa.x4;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository(value="X4ProjectRep")
public interface ProjectRep extends CrudRepository<Employee, Long> {
}
