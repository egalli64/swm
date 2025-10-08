package pa.x4;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("X4ProjectRep")
public interface ProjectRep extends CrudRepository<Project, Long> {
}
