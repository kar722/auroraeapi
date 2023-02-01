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
