package com.nighthawk.spring_portfolio.mvc.fact;

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
public class Facto {
    
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
    private String playerFact;

    // @NonNull, etc placed in params of constructor: "@NonNull @Size(min = 2, max = 30, message = "Name (2 to 30 chars)") String name"
    @NonNull
    @Size(min = 2, max = 30, message = "Name (2 to 30 chars)")
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;
    
    @Column(unique=false)
    private int teamsPlayedFor;

    @Column(unique=false)
    private String nationality;

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
    public Facto(String email, String playerFact, String name, Date dob, int teamsPlayedFor, String nationality) {
        this.email = email;
        this.playerFact = playerFact;
        this.name = name;
        this.dob = dob;
        this.teamsPlayedFor = teamsPlayedFor;
        this.nationality = nationality;
    }
    public String toString(){
        return ("{ \"email\": " + this.email + ", " + "\"playerFact\": " + this.playerFact + ", " + "\"name\": " + this.name + ", " + "\"dob\": " + this.dob + ", \"teamsplayedfor\": " + this.teamsPlayedFor + ", \"nationality\": " + this.nationality + " }" );
    }

    // A custom getter to return age from dob attribute
    public int getAge() {
        if (this.dob != null) {
            LocalDate birthDay = this.dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            return Period.between(birthDay, LocalDate.now()).getYears(); }
        return -1;
    }

    public String getAgeToString(){
        return ("{ \"name\": " + this.name + " ," + "\"age\": " + this.getAge() + " }" );
    }

    public String getNationality(){
        return nationality;
    }

    public String getNationalityToString(){
        return ("{ \"name\": " + this.name + " ," + "\"nationality\": " + this.getNationality() + " }" );
    }

    public String getPlayerFact(){
        return playerFact;
    }

    public String getPlayerFactToString(){
        return ("{ \"name\": " + this.name + " ," + "\"playerFact\": " + this.getPlayerFact() + " }" );
    }

    public static void main(String[] args) {
        // Person empty object
        Facto p1 = new Facto();

        // using gregorian calendar to initialize tester date object
        Date dob2 = new GregorianCalendar(2001, 0, 5).getTime();
        Facto p2 = new Facto("karthikv722@gmail.com", "Mudryk signed for Chelsea in January 2023 for $109m", "Mykhailo Mudryk", dob2, 4, "Ukraine");
        
        
        System.out.println(p1);
        System.out.println(p2);
     }


}