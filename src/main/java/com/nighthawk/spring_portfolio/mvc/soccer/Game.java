package com.nighthawk.spring_portfolio.mvc.soccer;

import java.time.LocalDate;

public class Game {
    
        private String team1Name;
        private String team2Name;
        private LocalDate gameDate;
        private int team1Score;
        private int team2Score;
    
    public LocalDate getGameDate() {
        return gameDate;
    }

    public String getTeam1Name() {
        return team1Name;
    }
    
    public String getTeam2Name() {
        return team2Name;
    }

    public int getTeam1Score() {
        return team1Score;
    }

    public int getTeam2Score() {
        return team2Score;
    }

    public void setGameDate(LocalDate gameDate) {
        this.gameDate = gameDate;
    }

    public void setTeam1Name(String team1Name) {
        this.team1Name = team1Name;
    }

    public void setTeam1Score(int team1Score) {
        this.team1Score = team1Score;
    }

    public void setTeam2Name(String team2Name) {
        this.team2Name = team2Name;
    }

    public void setTeam2Score(int team2Score) {
        this.team2Score = team2Score;
    }


}


/*
 Phase 1:

(Display game results for today of NBA, e.g.)

1/24/2023
GS Warriors 110, Heat 98
Lakers 102, Jazz 97

===============

Phase 2:
Choose 1 from the following:
1. NBA
2. NFL
3. MLB

(User enters 1, 2 or 3)

(Display game results for today of selected league, e.g.)

GS Warriors 110, Heat 98
Lakers 102, Jazz 97


=======================
Model classes
Game
String team1Name
String team2Name
Date gameDate
int team1Score
int team2Score
getTeam1Name()
getTeam2Name()
get....

ApiHelper
String apiKey
String baseUrl
List<Game> getGameData()

View classes
TerminalView
displayGameResults(List<Game> games)

Controller classes
Controller
main() - calls ApiHelper.getGameData() and TerminalView.displayGameResults()


 */