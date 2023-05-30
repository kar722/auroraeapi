package com.nighthawk.spring_portfolio.mvc.jokes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component // Scans Application for ModelInit Bean, this detects CommandLineRunner
public class JokesInit {
    
    // Inject repositories
    @Autowired JokesJpaRepository repository;
    
    @Bean
    CommandLineRunner run() {  // The run() method will be executed after the application starts
        return args -> {
            // Fail safe data validations

            // starting jokes
            final String[] jokesArray = {
                "Group B -- 1. Cornell vs 2. Syracuse",
                "Group B -- 1. FIU vs 2. Duke",
                "Group A -- 1. Pittsburg vs 2. Kentucky",
                "Group A -- 1. Marshal vs 2. Indiana",
                "Group C -- 1. Greensboro vs 2. Stanford",
                "Group C -- 1. Creigh vs 2. St Louis",
                "Group D -- 1. Denver vs 2. Akron",
                "Group D -- 1. Virginia vs 2. Penn State",
                "Group F -- 1. Clemson vs 2. UCLA",
                "Group F -- 1. Michigan vs 2. Lipscomb",
                "Group E -- 1. Ohio State vs 2. Florida",
                "Group E -- 1. Georgia Town vs 2. Tulsa",
                "Group G -- 1. Portland vs 2. Washington",
                "Group G -- 1. Highpoint vs 2. Oregon State",
                "Group H -- 1. SMU vs 2. UNH",
                "Group H -- 1. USF vs 2. MD"
            };

            // make sure Joke database is populated with starting jokes
            for (String joke : jokesArray) {
                List<Jokes> test = repository.findByJokeIgnoreCase(joke);  // JPA lookup
                if (test.size() == 0)
                    repository.save(new Jokes(null, joke, 0, 0)); //JPA save
            }
            
        };
    }
}
