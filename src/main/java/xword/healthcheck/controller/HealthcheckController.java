package xword.healthcheck.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author alex
 */
@RestController
@CrossOrigin
@RequestMapping("/healthcheck")
public class HealthcheckController {

    @RequestMapping(path="")
    public String healthcheck() {
        return "alive";
    }

}