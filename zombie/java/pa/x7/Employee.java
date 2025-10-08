package pa.x7;

import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity(name = "X7Employee")
@Table(name = "X7_EMPLOYEES")
public class Employee {
	public enum PhoneType {
		Home, Cell, Work
	};

	@Id
	@TableGenerator(name = "X_TABLE_GENERATOR", allocationSize = 1)
	@GeneratedValue(generator = "X_TABLE_GENERATOR")
	private long emp_id;

	@ElementCollection
	@CollectionTable(name = "x7_employee_phones", joinColumns = @JoinColumn(name = "EMP_ID"))
	@MapKeyEnumerated(EnumType.STRING)
	@MapKeyColumn(name = "phn_type")
	@Column(name = "phn_number")
	private Map<PhoneType, String> phones;

	protected Employee() {
	}

	public Employee(Map<PhoneType, String> phones) {
		this.phones = phones;
	}

	@Override
	public String toString() {
		return "Employee [id=" + emp_id + ", phones=" + phones + "]";
	}
}
