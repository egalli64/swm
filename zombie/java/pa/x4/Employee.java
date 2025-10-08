package pa.x4;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity(name = "X4Employee")
@Table(name = "X4_EMPLOYEES")
public class Employee {
	@Id
	@TableGenerator(name = "X_TABLE_GENERATOR", allocationSize = 1)
	@GeneratedValue(generator = "X_TABLE_GENERATOR")
	private long emp_id;

	@ManyToMany
	@JoinTable(name = "X4_EMP_PRJ", //
			joinColumns = @JoinColumn(name = "EMP_ID"), //
			inverseJoinColumns = @JoinColumn(name = "PRJ_ID")) //
	private Collection<Project> projects;

	protected Employee() {
	}

	public Employee(Collection<Project> projects) {
		this.projects = projects;
	}

	@Override
	public String toString() {
		return "Employee [id=" + emp_id + ", projects=" + projects + "]";
	}
}
