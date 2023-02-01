package com.nighthawk.spring_portfolio.mvc.nba;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class APIHelper {
    private static final String API_KEY = "39c4bf8c2emsh30b02ab6dc01dd9p13f427jsn690a650cf2ec";
    private static final String API_HOST = "sportscore1.p.rapidapi.com";

    public static List<Game> getGameData(){
        HttpRequest request = HttpRequest.newBuilder()
		.uri(URI.create("https://sportscore1.p.rapidapi.com/sports/1/teams?page=1"))
		.header("X-RapidAPI-Key", "39c4bf8c2emsh30b02ab6dc01dd9p13f427jsn690a650cf2ec")
		.header("X-RapidAPI-Host", "sportscore1.p.rapidapi.com")
		.method("GET", HttpRequest.BodyPublishers.noBody())
		.build();

try {
    HttpResponse<String>  response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    System.out.println(response.body());
} catch (IOException | InterruptedException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
}

        return null;
}

public static void main(String[] args) {
    getGameData();
}

}
