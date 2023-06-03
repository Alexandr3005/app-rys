

package com.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class MainController {

    @RequestMapping(value = {"/", "/{path:[^\\.]*}"})
    public String forward(@PathVariable(required = false) String path) {
        return "forward:/index.html";
    }
}