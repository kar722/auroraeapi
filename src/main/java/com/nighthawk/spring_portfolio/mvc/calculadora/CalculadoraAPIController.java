package com.nighthawk.spring_portfolio.mvc.calculadora;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/calculator")
public class CalculadoraAPIController {

    @GetMapping("/{expression}")
    public ResponseEntity<String> getResult(@PathVariable String expression) {

        // Returns jsonified result of expression with tokens and everything
        Calculadora a = new Calculadora(expression);
        String result = a.jsonify();
        if (result != null) {
            return new ResponseEntity<String>(result, HttpStatus.OK);
        }

        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);       
    }

}