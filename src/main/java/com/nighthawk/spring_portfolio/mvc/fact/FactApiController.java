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
    public ResponseEntity<List<Facto>> getPeople() {
        return new ResponseEntity<>( repository.findAllByOrderByNameAsc(), HttpStatus.OK);
    }

    /*
    GET individual Fact using ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Facto> getFact(@PathVariable long id) {
        Optional<Facto> optional = repository.findById(id);
        if (optional.isPresent()) {  // Good ID
            Facto   fact = optional.get();  // value from findByID
            return new ResponseEntity<>(fact, HttpStatus.OK);  // OK HTTP response: status code, headers, and body
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);       
    }

    @GetMapping("/getnationality/{id}")
    public String getnationality(@PathVariable long id) {
        Optional<Facto> optional = repository.findById(id);
        if (optional.isPresent()) {  // Good ID
            Facto fact = optional.get();  // value from findByID
            String nationalityToString = fact.getNationalityToString();
            return nationalityToString;
        }
        // Bad ID
        return "Error - Bad ID";       
    }

    @GetMapping("/getAge/{id}")
    public String getAge(@PathVariable long id) {
        Optional<Facto> optional = repository.findById(id);
        if (optional.isPresent()) {  // Good ID
            Facto   fact = optional.get();  // value from findByID
            String ageToString = fact.getAgeToString();
            return ageToString;
        }
        // Bad ID
        return "Error - Bad ID";       
    }

    @GetMapping("/getPlayerFact/{id}")
    public String getPlayerFact(@PathVariable long id) {
        Optional<Facto> optional = repository.findById(id);
        if (optional.isPresent()) {  // Good ID
            Facto fact = optional.get();  // value from findByID
            String ageToString = fact.getPlayerFactToString();
            return ageToString;
        }
        // Bad ID
        return "Error - Bad ID";       
    }

    /*
    DELETE individual Fact using ID
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Facto> deleteFact(@PathVariable long id) {
        Optional<Facto> optional = repository.findById(id);
        if (optional.isPresent()) {  // Good ID
            Facto fact = optional.get();  // value from findByID
            repository.deleteById(id);  // value from findByID
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
                                             @RequestParam("playerFact") String playerFact,
                                             @RequestParam("name") String name,
                                             @RequestParam("dob") String dobString,
                                             @RequestParam("teamsPlayedFor") int teamsPlayedFor,
                                             @RequestParam("nationality") String nationality) {
        Date dob;
        try {
            dob = new SimpleDateFormat("MM-dd-yyyy").parse(dobString);
        } catch (Exception e) {
            return new ResponseEntity<>(dobString +" error; try MM-dd-yyyy", HttpStatus.BAD_REQUEST);
        }
        // A fact object WITHOUT ID will create a new record with default roles as student
        Facto fact = new Facto(null, email, playerFact, name, dob, teamsPlayedFor, nationality, null);
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
        List<Facto> list = repository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(term, term);

        // return resulting list and status, error checking should be added
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /*
    The factStats API adds stats by Date to Fact table
    */
    @PostMapping(value = "/setStats", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Facto> factStats(@RequestBody final Map<String,Object> stat_map) {
        // find ID
        long id=Long.parseLong((String)stat_map.get("id"));
        Optional<Facto> optional = repository.findById((id));
        if (optional.isPresent()) {  // Good ID
            Facto  fact = optional.get();  // value from findByID
            // Extract Attributes from JSON
            Map<String, Object> attributeMap = new HashMap<>();
            for (Map.Entry<String,Object> entry : stat_map.entrySet())  {
                // Add all attribute other thaN "date" to the "attribute_map"
                if (!entry.getKey().equals("date") && !entry.getKey().equals("id"))
                    attributeMap.put(entry.getKey(), entry.getValue());
            }
            // Set Date and Attributes to SQL HashMap
            Map<String, Map<String, Object>> date_map = fact.getStats();
            if (date_map == null) date_map = new HashMap<>();
            date_map.put( (String) stat_map.get("date"), attributeMap );
            fact.setStats(date_map);  // BUG, needs to be customized to replace if existing or append if new
            repository.save(fact);  // conclude by writing the stats updates
            // return Fact with update Stats
            return new ResponseEntity<>(fact, HttpStatus.OK);
        }
        // return Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);


    
    }

}