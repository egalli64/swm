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
        Iterable<Organization> organizations = repo.findAll();

        int countriesSize = 0;
        for (Organization cur : organizations) {
            int size = cur.getCountries().size();
            if (size > countriesSize)
                countriesSize = size;
        }

        model.addAttribute("organizations", organizations);
        model.addAttribute("countriesSize", countriesSize);
        return "organizations";
    }

    @GetMapping("/organization")
    public String organization( //
            @RequestParam long id, //
            Model model) {
        logger.debug("Select organization with id " + id);

        Optional<Organization> opt = repo.findById(id);
        if (opt.isPresent()) {
            model.addAttribute("organization", opt.get());
            model.addAttribute("countries", repoCountry.findAll());
        } else {
            model.addAttribute("id", id);
        }
        return "organization";
    }

    @GetMapping("/organization/save")
    public String saveOrganization( //
            @RequestParam String name, //
            Model model) {
        Organization org = new Organization(name);
        repo.save(org);
        logger.debug("Save organization " + org);

        model.addAttribute("organizations", repo.findAll());
        return "organizations";
    }

    @GetMapping("/organization/delete")
    public String deleteOrganization( //
            @RequestParam long id, //
            Model model) {
        logger.debug("Delete organization " + id);
        repo.deleteById(id);

        model.addAttribute("message", String.format("Organization %d deleted", id));
        model.addAttribute("organizations", repo.findAll());
        return "organizations";
    }

    @GetMapping("/organization/add")
    public String addToOrganization( //
            @RequestParam(name = "country") String cid, //
            @RequestParam(name = "org") long oid, //
            Model model) {
        logger.debug(String.format("Adding country %s to organization %d", cid, oid));

        // ensure country is valid
        Optional<Country> optCountry = repoCountry.findById(cid);
        if (!optCountry.isPresent()) {
            model.addAttribute("message", String.format("Can't add country %s to this organization", cid));
        }

        Optional<Organization> optOrg = repo.findById(oid);
        if (optOrg.isPresent()) {
            Organization org = optOrg.get();
            if (optCountry.isPresent()) {
                org.getCountries().add(optCountry.get());
                repo.save(org);
            }
            model.addAttribute("organization", org);
            model.addAttribute("countries", repoCountry.findAll());
        } else {
            model.addAttribute("id", oid);
        }

        return "organization";
    }
}
