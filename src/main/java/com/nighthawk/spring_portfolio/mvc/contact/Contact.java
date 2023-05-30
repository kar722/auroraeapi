package com.nighthawk.spring_portfolio.mvc.contact;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.format.annotation.DateTimeFormat;

import com.vladmihalcea.hibernate.type.json.JsonType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/*
Person is a POJO, Plain Old Java Object.
First set of annotations add functionality to POJO
--- @Setter @Getter @ToString @NoArgsConstructor @RequiredArgsConstructor
The last annotation connect to database
--- @Entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@TypeDef(name="json", typeClass = JsonType.class)
public class Contact {
    
    // automatic unique identifier for Person record
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // email, playerFact, roles are key attributes to login and authentication
    @NotEmpty
    @Size(min=5)
    @Column(unique=true)
    @Email
    private String email;

    @NotEmpty
    private String company;

    // @NonNull, etc placed in params of constructor: "@NonNull @Size(min = 2, max = 30, message = "Name (2 to 30 chars)") String name"
    @NonNull
    @Size(min = 2, max = 30, message = "Name (2 to 30 chars)")
    private String name;

    @Column(unique=false)
    private String message;

    /* HashMap is used to store JSON for daily "stats"
    "stats": {
        "2022-11-13": {
            "calories": 2200,
            "steps": 8000
        }
    }
  */
    @Type(type="json")
    @Column(columnDefinition = "jsonb")
    private Map<String,Map<String, Object>> stats = new HashMap<>();


    // Constructor used when building object from an API
    public Contact(String email, String company, String name, String message) {
        this.email = email;
        this.company = company;
        this.name = name;
        this.message = message;
    }
    public String toString(){
        return ("{ \"email\": " + this.email + ", " + "\"company\": " + this.company + ", " + "\"name\": " + this.name + ", " + ", \"message\": " + this.message + " }" );
    }

    public String getMessage(){
        return message;
    }

    public String getMessageToString(){
        return ("{ \"name\": " + this.name + " ," + "\"message\": " + this.getMessage() + " }" );
    }

    public String getCompany(){
        return company;
    }

    public String getCompanyToString(){
        return ("{ \"name\": " + this.name + " ," + "\"company\": " + this.getCompany() + " }" );
    }

    public static void main(String[] args) {
        // Person empty object
        Contact p1 = new Contact();
        Contact p2 = new Contact("karthikv722@gmail.com", "Kar Tech", "Karthik Valluri", "Open the McCarthyism & the Read Scare, and the Google doc that goes with it.  Use the Pear Deck to answer all questions and complete all Activities in the Google Doc, and answer all questions in the Pear Deck as well.  When you are done, submit your complete Google Doc here.  The Pear Deck will submit automatically. ");
        
        System.out.println(p1);
        System.out.println(p2);
     }


}