package pa.x6a;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity(name = "X6aEmployee")
@Table(name = "X6_EMPLOYEES")
public class Employee {
	@Id
	@TableGenerator(name = "X_TABLE_GENERATOR", allocationSize = 1)
	@GeneratedValue(generator = "X_TABLE_GENERATOR")
	private long emp_id;

	@ElementCollection
	@CollectionTable(name = "x6_employee_phones", joinColumns = @JoinColumn(name = "EMP_ID"))
	@Column(name = "phone")
	@OrderBy("phone")
	private List<String> phones;

	protected Employee() {
	}

	public Employee(List<String> phones) {
		this.phones = phones;
	}

	@Override
	public String toString() {
		return "Employee [id=" + emp_id + ", phones=" + phones + "]";
	}
}
