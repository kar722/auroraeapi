package com.nighthawk.spring_portfolio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller  // HTTP requests are handled as a controller, using the @Controller annotation
public class New {

    // CONTROLLER handles GET request for /birds, maps it to birds() method
    @GetMapping("/new")
    public String new() {

        // load HTML VIEW (birds.html)
        return "new";

    }
}


