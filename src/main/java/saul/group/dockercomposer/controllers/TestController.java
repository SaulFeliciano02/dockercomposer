package saul.group.dockercomposer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @RequestMapping("/")
    public String pruebahome()
    {
        return "thymeleaf/homeUser";
    }

}