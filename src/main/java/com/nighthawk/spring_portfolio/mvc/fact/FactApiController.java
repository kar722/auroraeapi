package com.nighthawk.spring_portfolio.mvc.fact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/api/fact")
public class FactApiController {
    /*
    #### RESTful API ####
    Resource: https://spring.io/guides/gs/rest-service/
    */

    // Autowired enables Control to connect POJO Object through JPA
    @Autowired
    private FactJpaRepository repository;

    /*
    GET List of People
     */
    @GetMapping("/")
    public ResponseEntity<List<Fact>> getPeople() {
        return new ResponseEntity<>( repository.findAllByOrderByNameAsc(), HttpStatus.OK);
    }

    /*
    GET individual Fact using ID
     */
    @GetMapping("/{playerName}")
    public ResponseEntity<Fact> getFact(@PathVariable long playerName) {
        Optional<Fact> optional = repository.findById(playerName);
        if (optional.isPresent()) {  // Good ID
            Fact fact = optional.get();  // value from findByID
            return new ResponseEntity<>(fact, HttpStatus.OK);  // OK HTTP response: status code, headers, and body
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);       
    }

    @GetMapping("/getplayerfact/{playerName}")
    public String getnationality(@PathVariable long playerName) {
        Optional<Fact> optional = repository.findById(playerName);
        if (optional.isPresent()) {  // Good ID
            Fact fact = optional.get();  // value from findByID
            String getPlayerFactToString = fact.getPlayerFactToString();
            return getPlayerFactToString;
        }
        // Bad ID
        return "Error - Bad ID";       
    }

    /*
    DELETE individual Fact using ID
     */
    @DeleteMapping("/delete/{playerName}")
    public ResponseEntity<Fact> deleteFact(@PathVariable long playerName) {
        Optional<Fact> optional = repository.findById(playerName);
        if (optional.isPresent()) {  // Good ID
            Fact fact = optional.get();  // value from findByID
            repository.deleteById(playerName);  // value from findByID
            return new ResponseEntity<>(fact, HttpStatus.OK);  // OK HTTP response: status code, headers, and body
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /*
    POST Aa record by Requesting Parameters from URI
     */
    @PostMapping( "/post")
    public ResponseEntity<Object> postFact(@RequestParam("email") String email,
                                             @RequestParam("password") String password,
                                             @RequestParam("player's name") String playerName,
                                             @RequestParam("player's team") String playerTeam,
                                             @RequestParam("fact about player") String playerFact) {
        // A fact object WITHOUT ID will create a new record with default roles as student
        Fact fact = new Fact(email, password, playerName, playerTeam, playerFact);
        repository.save(fact);
        return new ResponseEntity<>(email +" is created successfully", HttpStatus.CREATED);
    }

    /*
    The factSearch API looks across database for partial match to term (k,v) passed by RequestEntity body
     */
    @PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> factSearch(@RequestBody final Map<String,String> map) {
        // extract term from RequestEntity
        String term = (String) map.get("term");

        // JPA query to filter on term
        List<Fact> list = repository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(term, term);

        // return resulting list and status, error checking should be added
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}