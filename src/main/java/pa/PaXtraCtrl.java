package pa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaXtraCtrl {
	private static final Logger logger = LoggerFactory.getLogger(PaXtraCtrl.class);

	@Autowired
	pa.x1.EmployeeRep repoX1;

	@Autowired
	pa.x2.EmployeeRep repoX2Emp;

	@Autowired
	pa.x2.WorkstationRep repoX2Wst;

	@Autowired
	pa.x2a.WorkstationRep repoX2aWst;

	@Autowired
	pa.x3.EmployeeRep repoX3Emp;

	@Autowired
	pa.x3.DepartmentRep repoX3Dep;

	@Autowired
	pa.x3a.DepartmentRep repoX3aDep;

	@Autowired
	pa.x4.EmployeeRep repoX4Emp;

	@Autowired
	pa.x4.ProjectRep repoX4Prj;

	@Autowired
	pa.x5.EmployeeRep repoX5Emp;

	@Autowired
	pa.x5.DepartmentRep repoX5Dep;

	@GetMapping("/xtra1")
	public String xtra1(Model model) {
		// Table Generator for PK
		List<String> messages = new ArrayList<>();

		try {
			for (int i = 0; i < 3; i++) {
				pa.x1.Employee emp = repoX1.save(new pa.x1.Employee());
				messages.add(String.format("%s saved", emp.toString()));
			}
		} catch (DataIntegrityViolationException dive) {
			messages.add(dive.getMessage());
			logger.error("Table Generator failure", dive);
		}
		model.addAttribute("employees", repoX1.findAll());

		model.addAttribute("messages", messages);
		return "xtra1";
	}

	@GetMapping("/xtra2")
	public String xtra2(Model model) {
		// One To One (Mono & Bidirectional)
		List<String> messages = new ArrayList<>();

		try {
			pa.x2.Workstation ws1 = repoX2Wst.save(new pa.x2.Workstation());
			messages.add("WS1 is " + ws1);
			pa.x2.Workstation ws2 = repoX2Wst.save(new pa.x2.Workstation());
			messages.add("WS2 is " + ws2);

			// cross the references between employee and workstation
			pa.x2.Employee emp1 = repoX2Emp.save(new pa.x2.Employee(ws2));
			messages.add("EMP1 is " + emp1);
			pa.x2.Employee emp2 = repoX2Emp.save(new pa.x2.Employee(ws1));
			messages.add("EMP2 is " + emp2);

			try {
				// this should throw an exception
				pa.x2.Employee emp3 = repoX2Emp.save(new pa.x2.Employee(ws1));
				messages.add("Unexpected: " + emp3);
			} catch (DataIntegrityViolationException dive) {
				messages.add("As expected: " + dive.getMessage());
				logger.info("X2 expected exception: " + dive.getMessage());
			}
		} catch (DataIntegrityViolationException dive) {
			messages.add("Unexpected: " + dive.getMessage());
			logger.info("X2 Unexpected exception", dive);
		}
		model.addAttribute("employees", repoX2Emp.findAll());
		model.addAttribute("workstations", repoX2Wst.findAll());

		// bidirectional
		model.addAttribute("workstations2", repoX2aWst.findAll());

		model.addAttribute("messages", messages);
		return "xtra2";
	}

	@GetMapping("/xtra3")
	public String xtra3(Model model) {
		List<String> messages = new ArrayList<>();

		// Many To One (and One To Many)
		try {
			pa.x3.Department dep1 = repoX3Dep.save(new pa.x3.Department());
			messages.add("DEP1 is " + dep1);
			pa.x3.Department dep2 = repoX3Dep.save(new pa.x3.Department());
			messages.add("DEP2 is " + dep2);

			pa.x3.Employee emp1 = repoX3Emp.save(new pa.x3.Employee(dep1));
			messages.add("EMP1 is " + emp1);
			pa.x3.Employee emp2 = repoX3Emp.save(new pa.x3.Employee(dep1));
			messages.add("EMP2 is " + emp2);
			pa.x3.Employee emp3 = repoX3Emp.save(new pa.x3.Employee(dep2));
			messages.add("EMP3 is " + emp3);
		} catch (DataIntegrityViolationException dive) {
			messages.add("Unexpected: " + dive.getMessage());
			logger.info("X3 Unexpected exception", dive);
		}
		model.addAttribute("employees", repoX3Emp.findAll());
		model.addAttribute("departments", repoX3Dep.findAll());

		// both ways
		model.addAttribute("departments2", repoX3aDep.findAll());

		model.addAttribute("messages", messages);
		return "xtra3";
	}

	@GetMapping("/xtra4")
	public String xtra4(Model model) {
		List<String> messages = new ArrayList<>();

		// Many To Many
		try {
			pa.x4.Project prj1 = repoX4Prj.save(new pa.x4.Project());
			messages.add("PRJ1 is " + prj1);
			pa.x4.Project prj2 = repoX4Prj.save(new pa.x4.Project());
			messages.add("PRJ2 is " + prj2);

			pa.x4.Employee emp1 = repoX4Emp.save(new pa.x4.Employee(Arrays.asList(prj1, prj2)));
			messages.add("EMP1 is " + emp1);
			pa.x4.Employee emp2 = repoX4Emp.save(new pa.x4.Employee(Arrays.asList(prj1, prj2)));
			messages.add("EMP2 is " + emp2);
			pa.x4.Employee emp3 = repoX4Emp.save(new pa.x4.Employee(Arrays.asList(prj2)));
			messages.add("EMP3 is " + emp3);
		} catch (DataIntegrityViolationException dive) {
			messages.add("Unexpected: " + dive.getMessage());
			logger.info("X3 Unexpected exception", dive);
		}
		model.addAttribute("employees", repoX4Emp.findAll());
		model.addAttribute("projects", repoX4Prj.findAll());

		model.addAttribute("messages", messages);
		return "xtra4";
	}

	@GetMapping("/xtra5")
	public String xtra5(Model model) {
		List<String> messages = new ArrayList<>();

		// Unidirectional One-to-Many
		try {
			pa.x5.Employee emp1 = repoX5Emp.save(new pa.x5.Employee());
			messages.add("EMP1 is " + emp1);
			pa.x5.Employee emp2 = repoX5Emp.save(new pa.x5.Employee());
			messages.add("EMP2 is " + emp2);
			pa.x5.Employee emp3 = repoX5Emp.save(new pa.x5.Employee());
			messages.add("EMP3 is " + emp3);

			pa.x5.Department dep1 = repoX5Dep.save(new pa.x5.Department(Arrays.asList(emp1, emp2)));
			messages.add("DEP1 is " + dep1);

			try {
				// this should throw an exception
				repoX5Dep.save(new pa.x5.Department(Arrays.asList(emp3, emp2)));
			} catch (DataIntegrityViolationException dive) {
				messages.add("As expected: " + dive.getMessage());
				logger.info("X5 expected exception: " + dive.getMessage());
			}
		} catch (DataIntegrityViolationException dive) {
			messages.add("Unexpected: " + dive.getMessage());
			logger.info("X5 Unexpected exception", dive);
		}
		model.addAttribute("employees", repoX5Emp.findAll());
		model.addAttribute("departments", repoX5Dep.findAll());

		model.addAttribute("messages", messages);
		return "xtra5";
	}
}
