package xword.healthcheck.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A simple route to check if this service is alive.
 *
 * @author alex
 */
@RestController
@CrossOrigin
@RequestMapping("/healthcheck")
public class HealthcheckController {

    /**
     * Returns 'alive'.
     *
     * @return String
     */
    @RequestMapping(path="")
    public String healthcheck() {
        return "alive";
    }

}