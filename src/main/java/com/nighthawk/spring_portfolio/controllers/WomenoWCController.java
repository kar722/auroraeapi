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
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;

@RestController // annotation to create a RESTful web services
@RequestMapping("/api/nfl")  //prefix of API
public class WomenoWCController {
    private JSONObject body; //last run result
    private HttpStatus status; //last run status
    String last_run = null; //last run day of month

    // GET Covid 19 Stats
    @GetMapping("/women")   //added to end of prefix as endpoint
    public ResponseEntity<JSONObject> getData() {

        //calls API once a day, sets body and status properties
        String today = new Date().toString().substring(0,10); 
        if (true)
        {
            try {  //APIs can fail (ie Internet or Service down)
                

                final String[][] wcmatches = {
                    {"A,New Zealand,1"},
                    {"A,Norway,3"},
                    {"A,Switzerland,5"},
                    {"A,Philippines, 8"},
                    {"B,Canada,9"},
                    {"B,Nigeria,6"},
                    {"B,Australia,6"},
                    {"B,Ireland,5"},
                    {"C,Japan,9"},
                    {"C,Costa Rica,2"},
                    {"C,Spain,6"},
                    {"D,Zambia,10"},
                    {"D,England,4"},
                    {"D,China,6"},
                    {"D,Denmark,2"},
                    {"E,US,8"},
                    {"E,Netherlands,4"},
                    {"E,Vietnam,9"},
                    {"F,France,9"},
                    {"F,Jamaica,5"},
                    {"F,Brazil,5"},
                    {"G,Sweden,10"},
                    {"G,South Africa,3"},
                    {"G,Italy,5"},
                    {"G,Argentina,4"},
                    {"H,Germany,8"},
                    {"H,Columbia,5"},
                    {"H,Morocco,6"},
                    {"H,South Korea,5"}
                };

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
