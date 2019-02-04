package pa.x5;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity(name = "X5Department")
@Table(name = "X5_DEPARTMENTS")
public class Department {
	@Id
	@TableGenerator(name = "X_TABLE_GENERATOR", allocationSize = 1)
	@GeneratedValue(generator = "X_TABLE_GENERATOR")
	private long dep_id;

	@OneToMany
	@JoinTable(name = "x5_dep_emp", //
			joinColumns = @JoinColumn(name = "DEP_ID"), //
			inverseJoinColumns = @JoinColumn(name = "EMP_ID"))
	private Collection<Employee> employees;

	public Department(Collection<Employee> employees) {
		this.employees = employees;
	}

	protected Department() {
	}

	public long getId() {
		return dep_id;
	}

	public Collection<Employee> getEmployees() {
		return employees;
	}

	@Override
	public String toString() {
		return "Department [dep_id=" + dep_id + ", employees=" + employees + "]";
	}
}
