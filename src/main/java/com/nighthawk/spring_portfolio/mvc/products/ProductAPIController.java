package com.nighthawk.spring_portfolio.mvc.products;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

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

@RestController // annotation to create a RESTful web services
@RequestMapping("/api/products")  //prefix of API
public class ProductAPIController {
    private JSONObject body; //last run result
    private HttpStatus status; //last run status
    private Product product;
    String last_run = null; //last run day of month

    // GET Covid 19 Stats

    @PostMapping("/add/{name}/{price}/{description}/{inStock}")
    public ResponseEntity<JSONObject> Add(@PathVariable String name,@PathVariable int price, @PathVariable String description, @PathVariable int inStock) throws Exception {
        Product product = new Product();
        product.insert(name,price,description,inStock);
        JSONArray result = product.get_data();
        this.body = new JSONObject();
        this.body.put("data", result);
        return new ResponseEntity<>(body,HttpStatus.OK);
    }
    
    @GetMapping("/getProducts")
    public ResponseEntity<JSONObject> getProducts() {
        Product product = new Product();
        JSONArray result = product.get_data();
        this.body = new JSONObject();
        this.body.put("data", result);
        return new ResponseEntity<>(body,HttpStatus.OK);
    }

    @GetMapping("/getProducts/{id}")
    public ResponseEntity<JSONObject> getProducts(@PathVariable int id) {
        Product product = new Product();
        JSONArray result = product.get_data(id);
        this.body = new JSONObject();
        this.body.put("data", result);
        return new ResponseEntity<>(body,HttpStatus.OK);
    }


    @PutMapping("/update/{id}/{name}/{price}/{description}/{inStock}")
    public ResponseEntity<JSONObject> Update(@PathVariable int id,@PathVariable String name,@PathVariable int price, @PathVariable String description, @PathVariable int inStock) throws Exception {
        Product product = new Product();
        product.updateData(id,name,price,description,inStock);
        JSONArray result = product.get_data();
        this.body = new JSONObject();
        this.body.put("data", result);
        return new ResponseEntity<>(body,HttpStatus.OK);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<JSONObject> Delete(@PathVariable int id) throws Exception {
        Product product = new Product();
        product.deleteItem(id);
        JSONArray result = product.get_data();
        this.body = new JSONObject();
        this.body.put("data", result);
        return new ResponseEntity<>(body,HttpStatus.OK);
    }

}