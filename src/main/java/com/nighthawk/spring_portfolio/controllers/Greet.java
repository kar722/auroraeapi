package com.nighthawk.spring_portfolio.controllers;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;



/* MVC code that shows defining a simple Model, calling View, and this file serving as Controller
 * Web Content with Spring MVCSpring Example: https://spring.io/guides/gs/serving-web-con
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@Controller  // HTTP requests are handled as a controller, using the @Controller annotation
public class Greet {
    // @GetMapping handles GET request for /greet, maps it to greeting() method
    @GetMapping("/greet")
    // @RequestParam handles variables binding to frontend, defaults, etc
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {

        // model attributes are visible to Thymeleaf when HTML is "pre-processed"
        model.addAttribute("name", name);

        // load HTML VIEW (greet.html)
        return "greet"; 

    }

      // CONTROLLER handles GET request for /birds, maps it to birds() method
    @GetMapping("/news")
    public String news(Model model) {
                
        HttpRequest request = HttpRequest.newBuilder()
		.uri(URI.create("https://nfl-schedule.p.rapidapi.com/v1/schedules"))
		.header("X-RapidAPI-Key", "9fb1283360mshedc514375b603d6p156a26jsna7cd4ca5744a")
		.header("X-RapidAPI-Host", "nfl-schedule.p.rapidapi.com")
		.method("GET", HttpRequest.BodyPublishers.noBody())
		.build();
		HttpResponse<String> response = null;
		try {
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(response.body());
        
        return "news";

    }

}