package pa.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pa.model.Country;
import pa.model.CountryRepository;
import pa.model.Organization;
import pa.model.OrganizationRepository;

@Controller
public class OrganizationCtrl {
	private static final Logger logger = LoggerFactory.getLogger(OrganizationCtrl.class);

	@Autowired
	OrganizationRepository repo;

	@Autowired
	CountryRepository repoCountry;

	@GetMapping("/organizations")
	public String allOrganizations(Model model) {
		logger.debug("All organizations");
		model.addAttribute("organizations", repo.findAll());
		return "organizations";
	}
	
	@GetMapping("/organization")
	public String organization( //
			@RequestParam int id, //
			Model model) {
		logger.debug("Select organization with id " + id);

		Optional<Organization> opt = repo.findById(id);
		if(opt.isPresent()) {
			model.addAttribute("organization", opt.get());
			model.addAttribute("countries", repoCountry.findAll());
		} else {
			model.addAttribute("id", id);
		}
		return "organization";
	}

	@GetMapping("/organization/save")
	public String saveOrganization( //
			@RequestParam int id, //
			@RequestParam String name, //
			Model model) {
		Organization org = new Organization(id, name);
		logger.debug("Save organization " + org);
		repo.save(org);

		model.addAttribute("organizations", repo.findAll());
		return "organizations";
	}

	@GetMapping("/organization/delete")
	public String deleteOrganization( //
			@RequestParam int id, //
			Model model) {
		logger.debug("Delete organization " + id);
		repo.deleteById(id);

		model.addAttribute("message", String.format("Organization %d deleted", id));
		model.addAttribute("organizations", repo.findAll());
		return "organizations";
	}

	@GetMapping("/organization/add")
	public String addToOrganization( //
			@RequestParam String country, //
			@RequestParam int org, //
			Model model) {
		logger.debug(String.format("Adding country %s to organization %d", country, org));

		// ensure country is valid
		Optional<Country> optCountry = repoCountry.findById(country);
		if (!optCountry.isPresent()) {
			model.addAttribute("message", String.format("Can't add country %s to this organization", country));
		}

		Optional<Organization> opt = repo.findById(org);
		if(opt.isPresent()) {
			Organization cur = opt.get();
			if(optCountry.isPresent()) {
				cur.getCountries().add(optCountry.get());
				repo.save(cur);
			}
			model.addAttribute("organization", cur);
			model.addAttribute("countries", repoCountry.findAll());
		} else {
			model.addAttribute("id", org);
		}

		return "organization";
	}	
}
