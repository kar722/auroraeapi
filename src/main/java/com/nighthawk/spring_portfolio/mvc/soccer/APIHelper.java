package com.nighthawk.spring_portfolio.mvc.soccer;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class APIHelper {
    private static final String API_KEY = "39c4bf8c2emsh30b02ab6dc01dd9p13f427jsn690a650cf2ec";
    private static final String API_HOST = "sportscore1.p.rapidapi.com";

    public static List<Team> getGameData(){
        HttpRequest request = HttpRequest.newBuilder()
		.uri(URI.create("https://sportscore1.p.rapidapi.com/sports/1/teams?page=1"))
		.header("X-RapidAPI-Key", "39c4bf8c2emsh30b02ab6dc01dd9p13f427jsn690a650cf2ec")
		.header("X-RapidAPI-Host", "sportscore1.p.rapidapi.com")
		.method("GET", HttpRequest.BodyPublishers.noBody())
		.build();

        List<Team> teamList = new ArrayList<>();
try {
    HttpResponse<String>  response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    System.out.println(response.body());

    String jsonString = response.body();

    //Item itemWithOwner = new ObjectMapper().readValue(json, Item.class);
    Root root = new ObjectMapper().readValue(jsonString, Root.class);
    List<Datum> dataList = root.data;
    for(Datum datum: dataList) {
        Team team = new Team();
        team.setCountry(datum.country);
        team.setLogo(datum.logo);
        team.setName(datum.name);
        teamList.add(team);
    }


} catch (IOException | InterruptedException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
}

        return teamList;
}

public static void main(String[] args) {
    List<Team> teams = getGameData();
    for (Team t: teams) {
        System.out.println(t);
    }
}

}
