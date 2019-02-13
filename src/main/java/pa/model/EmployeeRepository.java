package pa.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    List<Employee> findByLastName(String lastName);

    List<Employee> findByJobIdOrderById(String jobId);

    List<Employee> findByJobIdOrderByLastName(String jobId);

    List<Employee> findByJobIdOrderByFirstName(String jobId);

    Optional<Employee> findByIdAndJobId(Long id, String jobId);

    @Query("select e from Employee e where lower(e.firstName) like '%h%' order by e.firstName")
    List<Employee> findWhereNameContainsH();
}
