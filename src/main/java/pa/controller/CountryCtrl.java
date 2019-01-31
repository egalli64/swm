package pa.controller;

import java.util.List;
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
import pa.model.Region;
import pa.model.RegionRepository;

@Controller
public class CountryCtrl {
	private static final Logger logger = LoggerFactory.getLogger(CountryCtrl.class);

	@Autowired
	CountryRepository repo;

	@Autowired
	RegionRepository regionRepo;

	@GetMapping("/countries")
	public String allCountries(Model model) {
		logger.debug("All countries");
		model.addAttribute("countries", repo.findAll());
		return "countries";
	}

	@GetMapping("/countries/select")
	public String selectCountry( //
			@RequestParam String id, //
			Model model) {
		Optional<Country> country = repo.findById(id.toUpperCase());
		logger.debug(String.format("Country with id %s is %s", id, country));

		model.addAttribute("country", country.orElse(new Country(id, "Unknown", null)));
		return "country";
	}

	@GetMapping("/countries/startingBy")
	public String startingBy( //
			@RequestParam String name, //
			Model model) {
		logger.debug("countries starting by " + name);
		List<Country> countries = repo.findByNameLike(name + "%");

		model.addAttribute("message", " with name starting by " + name);
		model.addAttribute("countries", countries);
		return "countries";
	}

	@GetMapping("/countries/region")
	public String countriesByRegion( //
			@RequestParam int id, //
			Model model) {
		Optional<Region> region = regionRepo.findById(id);
		logger.debug("Countries by region " + id);

		if (region.isPresent()) {
			Region cur = region.get();
			List<Country> countries = repo.findByRegion(cur);
			model.addAttribute("countries", countries);
			model.addAttribute("region", cur);
		} else {
			model.addAttribute("region", new Region(id, ""));
		}

		return "countriesByRegion";
	}

	@GetMapping("/countries/save")
	public String saveCountry( //
			@RequestParam String cid, //
			@RequestParam String name, //
			@RequestParam int rid, //
			Model model) {
		Optional<Region> region = regionRepo.findById(rid);
		if (region.isPresent()) {
			Region cur = region.get();
			Country country = new Country(cid.toUpperCase(), name, cur);
			logger.debug("Save country " + country);
			repo.save(country);
			model.addAttribute("region", cur);
			model.addAttribute("countries", repo.findByRegion_id(rid));
			return "countriesByRegion";
		} else {
			logger.error(String.format("Can't save country %d: missing region %d", cid, rid));
			model.addAttribute("countries", repo.findAll());
			return "countries";
		}
	}

	@GetMapping("/countries/delete")
	public String deleteCountry( //
			@RequestParam String cid, //
			@RequestParam int rid, //
			Model model) {
		logger.debug("Delete country " + cid);

		repo.deleteById(cid);

		Optional<Region> region = regionRepo.findById(rid);
		if (region.isPresent()) {
			Region cur = region.get();
			model.addAttribute("region", cur);
			model.addAttribute("countries", repo.findByRegion(cur));
			return "countriesByRegion";
		} else {
			logger.warn("Missing region " + rid);
			model.addAttribute("countries", repo.findAll());
			return "countries";
		}
	}
}
