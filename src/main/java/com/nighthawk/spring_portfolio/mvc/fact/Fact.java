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
fact is a POJO, Plain Old Java Object.
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
public class Fact {
    
    // automatic unique identifier for fact record
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // email, password, roles are key attributes to login and authentication
    @NotEmpty
    @Size(min=5)
    @Column(unique=true)
    @Email
    private String email;

    @NotEmpty
    private String password;

    // @NonNull, etc placed in params of constructor: "@NonNull @Size(min = 2, max = 30, message = "Name (2 to 30 chars)") String name"
    @NonNull
    @Size(min = 2, max = 30, message = "Name (2 to 30 chars)")
    private String playerName;
    
    @Column(unique=false)
    private String playerTeam;

    @Column(unique=false)
    private String playerFact;

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
    public Fact(String email, String password, String playerName, String playerTeam, String playerFact) {
        this.email = email;
        this.password = password;
        this.playerName = playerName;
        this.playerTeam = playerTeam;
        this.playerFact = playerFact;
    }
    public String toString(){
        return ("{ \"email\": " + this.email + ", " + "\"password\": " + this.password + ", " + "\"player's name\": " + this.playerName + ", " + ", \"player's team\": " + this.playerTeam + ", \"Fact about player\": " + this.playerFact + " }" );
    }

    public String getPlayerFact(){
        return playerFact;
    }

    public String getPlayerFactToString(){
        return ("{ \"player's name\": " + this.playerName + " ," + "\"Fact about player\": " + this.getPlayerFact() + " }" );
    }

    public static void main(String[] args) {
        // fact empty object
        Fact p1 = new Fact();

        // using gregorian calendar to initialize tester date object
        Fact p2 = new Fact("karthikv722@gmail.com", "karthikishim", "Mykhailo Mudryk", "Chelsea F.C.", "Mudryk has 88 pac on his base FIFA 23 card");
        
        System.out.println(p1);
        System.out.println(p2);
     }


}