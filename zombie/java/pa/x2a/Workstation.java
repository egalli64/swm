package pa.x2a;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import pa.x2.Employee;

@Entity(name="X2aWorkstation")
@Table(name="X2_WORKSTATIONS")
public class Workstation {
	@Id
	@TableGenerator(name = "X_TABLE_GENERATOR", allocationSize = 1)
	@GeneratedValue(generator = "X_TABLE_GENERATOR")
	private long wst_id;

	public long getId() {
		return wst_id;
	}

	@OneToOne(mappedBy="workstation")
	private Employee employee;

	@Override
	public String toString() {
		return "Workstation [id=" + wst_id + ", employee=" + employee + "]";
	}
}
