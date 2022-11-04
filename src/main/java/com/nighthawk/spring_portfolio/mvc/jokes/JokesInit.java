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
                "Group B -- 1. Porto vs 2. Atletico",
                "Group B -- 1. Leverkusen vs 2. Club Brugge",
                "Group A -- 1. Liverpool vs 2. Napoli",
                "Group A -- 1. Rangers vs 2. Ajax",
                "Group C -- 1. Bayern vs 2. Inter",
                "Group C -- 1. Plzeň vs 2. Barcelona",
                "Group D -- 1. Sporting CP vs 2. Frankfurt",
                "Group D -- 1. Marseille vs 2. Tottenham",
                "Group F -- 1. Real Madrid vs 2. Celtic",
                "Group F -- 1. Shakhtar vs 2. Leipzig",
                "Group E -- 1. Chelsea vs 2. Dinamo Zagreb",
                "Group E -- 1. Milan vs 2. Salzburg",
                "Group G -- 1. Man City vs 2. Sevilla",
                "Group G -- 1. Copenhagen vs 2. Dortmund",
                "Group H -- 1. Juventus vs 2. Paris",
                "Group H -- 1. M. Haifa vs 2. Benfica"
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
