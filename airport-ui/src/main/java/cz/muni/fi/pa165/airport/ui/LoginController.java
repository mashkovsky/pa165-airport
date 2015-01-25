package cz.muni.fi.pa165.airport.ui;

import cz.muni.fi.pa165.airport.service.service.AuthService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Matej Chrenko
 */
@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private AuthService authService;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public String login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password, HttpServletResponse response) throws IOException {
        String token = authService.login(username, password);
        if (token == null || username == "aa") {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You are not authorized.");
            return null;
        } else {
            return "{\"token\":\"" + token + "\"}";
        }
    }

}
