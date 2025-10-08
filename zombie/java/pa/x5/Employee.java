package pa.x5;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity(name="X5Employee")
@Table(name="X5_EMPLOYEES")
public class Employee {
	@Id
	@TableGenerator(name = "X_TABLE_GENERATOR", allocationSize = 1)
	@GeneratedValue(generator = "X_TABLE_GENERATOR")
	private long emp_id;

	public Employee() {
	}

	@Override
	public String toString() {
		return "Employee [id=" + emp_id + "]";
	}
}
