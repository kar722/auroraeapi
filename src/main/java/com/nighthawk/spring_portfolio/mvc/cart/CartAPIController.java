package com.nighthawk.spring_portfolio.mvc.cart;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import org.apache.catalina.connector.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController // annotation to create a RESTful web services
@RequestMapping("/api/cart")  //prefix of API
public class CartAPIController {
    private JSONObject body; //last run result
    private HttpStatus status; //last run status
    private Cart cart;
    String last_run = null; //last run day of month

    @GetMapping("/getCart/{user}")
    public ResponseEntity<JSONObject> getCart(@PathVariable int uid) {
        Cart cart = new Cart();
        JSONArray result = cart.get_data(uid);
        this.body = new JSONObject();
        this.body.put("data", result);
        return new ResponseEntity<>(body,HttpStatus.OK);
    }
    @GetMapping("/getCart")
    public ResponseEntity<JSONObject> getCart() {
        Cart cart = new Cart();
        JSONArray result = cart.get_data();
        this.body = new JSONObject();
        this.body.put("data", result);
        return new ResponseEntity<>(body,HttpStatus.OK);
    }

    @PostMapping("/addItem/{item}/{user}/{quantity}/{uid}")
    public ResponseEntity<JSONObject> addItem(@PathVariable int item,@PathVariable String user,@PathVariable int quantity,@PathVariable int uid) {
        Cart cart = new Cart();
        cart.insert(item,user,quantity,uid);
        JSONArray result = cart.get_data();
        this.body = new JSONObject();
        this.body.put("data", result);
        return new ResponseEntity<>(body,HttpStatus.OK);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<JSONObject> delete(@PathVariable int id) {
        Cart cart = new Cart();
        cart.deleteItem(id);
        JSONArray result = cart.get_data();
        this.body = new JSONObject();
        this.body.put("data", result);
        return new ResponseEntity<>(body,HttpStatus.OK);

    }

    @PutMapping("update/{id}/{quantity}")
    public ResponseEntity<JSONObject> update(@PathVariable int id, @PathVariable int quantity) {
        Cart cart = new Cart();
        cart.updateData(id, quantity);
        JSONArray result = cart.get_data();
        this.body = new JSONObject();
        this.body.put("data",result);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
