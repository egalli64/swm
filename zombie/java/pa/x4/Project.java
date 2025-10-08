package pa.x4;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity(name="X4Project")
@Table(name="X4_PROJECTS")
public class Project {
	@Id
	@TableGenerator(name = "X_TABLE_GENERATOR", allocationSize = 1)
	@GeneratedValue(generator = "X_TABLE_GENERATOR")
	private long prj_id;

	public Project() {
	}
	
	@Override
	public String toString() {
		return "Project [id=" + prj_id + "]";
	}
}
