package com.nighthawk.spring_portfolio.controllers;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Date;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController // annotation to create a RESTful web services
@RequestMapping("/api/nfl")  //prefix of API
public class Qatar2022Controller {
    private JSONObject body; //last run result
    private HttpStatus status; //last run status
    String last_run = null; //last run day of month

    // GET Covid 19 Stats
    @GetMapping("/qatar")   //added to end of prefix as endpoint
    public ResponseEntity<JSONObject> getData() {

        //calls API once a day, sets body and status properties
        String today = new Date().toString().substring(0,10); 
        if (true)
        {
            try {  //APIs can fail (ie Internet or Service down)
                

                final String[][] wcmatches = {
                    {"Qatar,2"},
                    {"Ecuador,4"},
                    {"Senegal,5"},
                    {"Netherlands, 7"},
                    {"England,7"},
                    {"Iran,4"},
                    {"US,5"},
                    {"Wales,7"},
                    {"Argentina,7"},  
                    {"Saudi Arabia,4"},
                    {"Mexico,6"},
                    {"Poland,6"}
                    {"A,Qatar,1"},
                    {"A,Ecuador,3"},
                    {"A,Senegal,5"},
                    {"A,Netherlands, 8"},
                    {"B,England,9"},
                    {"B,Iran,6"},
                    {"B,US,6"},
                    {"B,Wales,5"},
                    {"C,Argentina,9"},
                    {"C,Saudi Arabia,2"},
                    {"C,Mexico,6"},
                    {"D,France,10"},
                    {"D,Australia,4"},
                    {"D,Denmark,6"},
                    {"D,Tunisia,2"},
                    {"E,Spain,8"},
                    {"E,Costa Rica,4"},
                    {"E,Germany,9"},
                    {"E,Japan,6"},
                    {"F,Belgium,9"},
                    {"F,Canada,5"},
                    {"F,Morocco,5"},
                    {"F,Croatia,7"},
                    {"G,Brazil,10"},
                    {"G,Serbia,3"},
                    {"G,Switzerland,5"},
                    {"G,Cameroon,4"},
                    {"H,Portugal,8"},
                    {"H,Ghana,5"},
                    {"H,Uruguay,6"},
                    {"H,South Korea,5"}
                };
                // Gson gson = new Gson();
                // String matches = gson.toJson(wcmatches);
                // Gson gson = new GsonBuilder().create();
                // gson.toJson(wcmatches);
                // this.body = (JSONObject) new JSONParser().parse(wcmatches);
                this.body = new JSONObject();

                JSONArray jsonArray = new JSONArray();
                for (String[] ca : wcmatches) {
                    JSONArray arr = new JSONArray();
                    for (String c : ca) {
                        arr.add((c)); // or some other conversion
                    }
                    jsonArray.add(arr);
                }

                this.body.put("matches", jsonArray);
                



                //RapidAPI header
                // HttpRequest request = HttpRequest.newBuilder()
                //     .uri(URI.create("https://opentdb.com/api.php?amount=1&category=21&difficulty=medium&type=multiple"))
                //     // .header("X-RapidAPI-Key", "9fb1283360mshedc514375b603d6p156a26jsna7cd4ca5744a")
                //     // .header("X-RapidAPI-Host", "nfl-schedule.p.rapidapi.com")
                //     .method("GET", HttpRequest.BodyPublishers.noBody())
                //     .build();

                // //RapidAPI request and response
                // HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

                // //JSONParser extracts text body and parses to JSONObject
                // this.body = (JSONObject) new JSONParser().parse(response.body());
                // this.status = HttpStatus.OK;  //200 success
                // this.last_run = today;
                // System.out.println(response.body());

            }
            catch (Exception e) {  //capture failure info
                HashMap<String, String> status = new HashMap<>();
                status.put("status", "RapidApi failure: " + e);

                //Setup object for error
                this.body = (JSONObject) status;
                this.status = HttpStatus.INTERNAL_SERVER_ERROR; //500 error
                this.last_run = null;
            }
        }

        //return JSONObject in RESTful style
        return new ResponseEntity<>(body, HttpStatus.OK);

        
    }

}
