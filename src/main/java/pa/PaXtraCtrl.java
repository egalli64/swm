package pa;

import java.util.ArrayList;
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

	@GetMapping("/xtra")
	public String employees(Model model) {
		List<String> messages = new ArrayList<>();

		// X1
		messages.add("Table Generator for PK");
		try {
			repoX1.save(new pa.x1.Employee());
			messages.add("A new employee has been added using the Table Generator");
		} catch (DataIntegrityViolationException dive) {
			messages.add(dive.getMessage());
			logger.error("X1 Table Generator failure", dive);
		}
		model.addAttribute("x1Employees", repoX1.findAll());

		// X2
		messages.add("One To One");
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
		model.addAttribute("x2Employees", repoX2Emp.findAll());
		model.addAttribute("x2Workstations", repoX2Wst.findAll());		

		// X2a
		messages.add("Bidirectional One To One");
		model.addAttribute("x2aWorkstations", repoX2aWst.findAll());		

		// X3
		messages.add("Many To One");
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
		model.addAttribute("x3Employees", repoX3Emp.findAll());
		model.addAttribute("x3Departments", repoX3Dep.findAll());		

		// X3a
		messages.add("Many To One and One To Many");
		model.addAttribute("x3aDepartments", repoX3aDep.findAll());		

		// X4
		messages.add("Many To Many");
		
		model.addAttribute("messages", messages);
		return "xtra";
	}
}
