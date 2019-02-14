package pa.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    List<Employee> findByLastName(String lastName);

    List<Employee> findByJobIdOrderById(String jobId);

    List<Employee> findByJobIdOrderByLastName(String jobId);

    List<Employee> findByJobIdOrderByFirstName(String jobId);

    Optional<Employee> findByIdAndJobId(Long id, String jobId);

    // @Query ... moved in META-INF/jpa-named-queries.properties
    List<Employee> findWhereNameContainsH();
}
