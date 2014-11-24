package cz.muni.fi.pa165.airport.ui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Mariia Schevchenko
 */
@Controller
public class FlightController {


    @RequestMapping("/")
    public String index(Model model) {
        return "hi";
    }

}
