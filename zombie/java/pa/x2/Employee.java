package pa.x2;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity(name = "X2Employee")
@Table(name = "X2_EMPLOYEES")
public class Employee {
	@Id
	@TableGenerator(name = "X_TABLE_GENERATOR", allocationSize = 1)
	@GeneratedValue(generator = "X_TABLE_GENERATOR")
	private long emp_id;

	@OneToOne
	@JoinColumn(name = "wst_id")
	private Workstation workstation;

	protected Employee() {
	}

	public Employee(Workstation workstation) {
		this.workstation = workstation;
	}

	public long getId() {
		return emp_id;
	}

	@Override
	public String toString() {
		return "Employee [id=" + emp_id + ", ws=" + workstation + "]";
	}
}
