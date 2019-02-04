package pa.x2;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity(name="X2Workstation")
@Table(name="X2_WORKSTATIONS")
public class Workstation {
	@Id
	@TableGenerator(name = "X_TABLE_GENERATOR", allocationSize = 1)
	@GeneratedValue(generator = "X_TABLE_GENERATOR")
	private long wst_id;

	public long getId() {
		return wst_id;
	}

	@Override
	public String toString() {
		return "Workstation [id=" + wst_id + "]";
	}
}
