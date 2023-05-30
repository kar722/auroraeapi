package com.nighthawk.spring_portfolio.mvc.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/api/contact")
public class ContactApiController {
    /*
    #### RESTful API ####
    Resource: https://spring.io/guides/gs/rest-service/
    */

    // Autowired enables Control to connect POJO Object through JPA
    @Autowired
    private ContactJpaRepository repository;

    /*
    GET List of People
     */
    @GetMapping("/")
    public ResponseEntity<List<Contact>> getContacts() {
        return new ResponseEntity<>( repository.findAllByOrderByNameAsc(), HttpStatus.OK);
    }

    /*
    GET individual Person using ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContact(@PathVariable long id) {
        Optional<Contact> optional = repository.findById(id);
        if (optional.isPresent()) {  // Good ID
            Contact contact = optional.get();  // value from findByID
            return new ResponseEntity<>(contact, HttpStatus.OK);  // OK HTTP response: status code, headers, and body
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);       
    }

    @GetMapping("/getMessage/{id}")
    public String getMessage(@PathVariable long id) {
        Optional<Contact> optional = repository.findById(id);
        if (optional.isPresent()) {  // Good ID
            Contact contact = optional.get();  // value from findByID
            String messageToString = contact.getMessageToString();
            return messageToString;
        }
        // Bad ID
        return "Error - Bad ID";
    }

    @GetMapping("/getCompany/{id}")
    public String getCompany(@PathVariable long id) {
        Optional<Contact> optional = repository.findById(id);
        if (optional.isPresent()) {  // Good ID
            Contact contact = optional.get();  // value from findByID
            String companyToString = contact.getCompanyToString();
            return companyToString;
        }
        // Bad ID
        return "Error - Bad ID";       
    }

    /*
    DELETE individual Person using ID
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Contact> deleteContact(@PathVariable long id) {
        Optional<Contact> optional = repository.findById(id);
        if (optional.isPresent()) {  // Good ID
            Contact contact = optional.get();  // value from findByID
            repository.deleteById(id);  // value from findByID
            return new ResponseEntity<>(contact, HttpStatus.OK);  // OK HTTP response: status code, headers, and body
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
    }

    /*
    POST Aa record by Requesting Parameters from URI
     */
    @PostMapping( "/post")
    public ResponseEntity<Object> postContact(@RequestParam("email") String email,
                                             @RequestParam("company") String company,
                                             @RequestParam("name") String name,
                                             @RequestParam("message") String message) {
        // A person object WITHOUT ID will create a new record with default roles as student
        Contact contact = new Contact(email, company, name, message);
        repository.save(contact);
        return new ResponseEntity<>(email +" has successfully contacted our team", HttpStatus.CREATED);
    }

    /*
    The personSearch API looks across database for partial match to term (k,v) passed by RequestEntity body
     */
    @PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> contactSearch(@RequestBody final Map<String,String> map) {
        // extract term from RequestEntity
        String term = (String) map.get("term");

        // JPA query to filter on term
        List<Contact> list = repository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(term, term);

        // return resulting list and status, error checking should be added
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}