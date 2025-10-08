package pa.x3;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity(name="X3Employee")
@Table(name="X3_EMPLOYEES")
public class Employee {
	@Id
	@TableGenerator(name = "X_TABLE_GENERATOR", allocationSize = 1)
	@GeneratedValue(generator = "X_TABLE_GENERATOR")
	private long emp_id;

	@ManyToOne
	@JoinColumn(name="dep_id")
	private Department dep;
	
	public Employee(Department dep) {
		this.dep = dep;
	}

	protected Employee() {
	}

	@Override
	public String toString() {
		return "Employee [id=" + emp_id + ", department=" + dep + "]";
	}
}
