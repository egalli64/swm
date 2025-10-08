package pa.x3a;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import pa.x3.Employee;

@Entity(name="X3aDepartment")
@Table(name="X3_DEPARTMENTS")
public class Department {
	@Id
	@TableGenerator(name = "X_TABLE_GENERATOR", allocationSize = 1)
	@GeneratedValue(generator = "X_TABLE_GENERATOR")
	private long dep_id;

	public long getId() {
		return dep_id;
	}
	
	@OneToMany(mappedBy="dep")
	private Collection<Employee> employees;

	@Override
	public String toString() {
		return "Department [id=" + dep_id + ", employees=" + employees + "]";
	}
}
