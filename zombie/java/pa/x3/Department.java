package pa.x3;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity(name="X3Department")
@Table(name="X3_DEPARTMENTS")
public class Department {
	@Id
	@TableGenerator(name = "X_TABLE_GENERATOR", allocationSize = 1)
	@GeneratedValue(generator = "X_TABLE_GENERATOR")
	private long dep_id;

	public long getId() {
		return dep_id;
	}

	@Override
	public String toString() {
		return "Department [id=" + dep_id + "]";
	}
}
