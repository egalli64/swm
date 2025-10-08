package pa;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pa.model.Region;

@Controller
@PropertySource("login.properties")
public class PaController {
    private static final Logger logger = LoggerFactory.getLogger(PaController.class);

    @Value("${admin}")
    private String admin;

    @Value("${admin.password}")
    private String adminPwd;

    @Value("${user}")
    private String myUsr;

    @Value("${user.password}")
    private String myUsrPwd;

    // this is not a good idea!
    @GetMapping("/login")
    public String loginByGet( //
            @RequestParam(name = "user", defaultValue = "Unknown") String user, //
            @RequestParam(name = "password") String password, //
            Model model) {
        logger.warn("Unsafe login attempt for user: " + user);

        model.addAttribute("user", user);
        return "/checkPassword";
    }

    @PostMapping("/login")
    public String login( //
            @RequestParam(name = "user") String user, //
            @RequestParam(name = "password") String password, //
            Model model) {
        logger.debug("Login attempt for user: " + user);

        model.addAttribute("user", user);
        if (user.equals(admin)) {
            if (password.equals(adminPwd)) {
                return "/manager";
            }
        } else if (user.equals(myUsr) && password.equals(myUsrPwd)) {
            return "/employees";
        }
        return "/checkPassword";
    }

    @GetMapping("/hello")
    public String hello() {
        return "/hello";
    }

    @GetMapping("/guest")
    public String guest( //
            @RequestParam(name = "user") String user, //
            Model model) {
        model.addAttribute("user", user);
        return "/guest";
    }

    @GetMapping("/simple")
    public String showCountries(Model model) {
        logger.debug("Show thymeLeaf simple test page");
        model.addAttribute("data", Arrays.asList( //
                new Region(1, "North Europe"), //
                new Region(2, "South Europe")) //
        );
        model.addAttribute("user", "Tom Jones");

        model.addAttribute("users", Arrays.asList( //
                new String[] { admin, adminPwd }, //
                new String[] { myUsr, myUsrPwd }) //
        );

        return "/simple";
    }
}
