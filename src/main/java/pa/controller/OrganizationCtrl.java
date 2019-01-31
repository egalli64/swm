package pa.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pa.model.OrganizationRepository;

@Controller
public class OrganizationCtrl {
	private static final Logger logger = LoggerFactory.getLogger(OrganizationCtrl.class);

	@Autowired
	OrganizationRepository repo;

	@GetMapping("/organizations")
	public String allOrganizations(Model model) {
		logger.debug("All organizations");
		model.addAttribute("organizations", repo.findAll());
		return "organizations";
	}
}
