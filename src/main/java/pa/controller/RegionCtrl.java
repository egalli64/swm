package pa.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pa.model.Region;
import pa.model.RegionRepository;

@Controller
public class RegionCtrl {
    private static final Logger logger = LoggerFactory.getLogger(RegionCtrl.class);

    @Autowired
    private RegionRepository repo;

    @GetMapping("/regions")
    public String showRegions(Model model) {
        logger.debug("Get all regions");
        model.addAttribute("regions", repo.findAll());
        return "regions";
    }

    @GetMapping("/region")
    public String selectRegion( //
            @RequestParam long id, //
            Model model) {
        logger.debug("Get region " + id);

        Optional<Region> opt = repo.findById(id);
        if (opt.isPresent()) {
            model.addAttribute("region", opt.get());
        } else {
            model.addAttribute("id", id);
        }
        return "region";
    }

    @GetMapping("/region/save")
    public String saveRegion( //
            @RequestParam long id, //
            @RequestParam String name, //
            Model model) {
        Region region = new Region(id, name);
        logger.debug("Save region " + region);
        repo.save(region);

        model.addAttribute("regions", repo.findAll());
        return "regions";
    }

    @GetMapping("/region/delete")
    public String deleteRegion( //
            @RequestParam long id, //
            Model model) {
        logger.debug("Delete region " + id);
        repo.deleteById(id);

        model.addAttribute("regions", repo.findAll());
        return "regions";
    }

    @GetMapping("/my/regions")
    public String myRegions(Model model) {
        logger.debug("Get my regions");
        model.addAttribute("all", repo.myFindAll());
        model.addAttribute("allNames", repo.myFindAllNames());
        model.addAttribute("aRegions", repo.myStartingByA());
        return "/my/regions";
    }

}
