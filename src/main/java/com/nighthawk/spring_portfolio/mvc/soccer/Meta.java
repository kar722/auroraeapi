package com.nighthawk.spring_portfolio.mvc.soccer;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Meta {
    public int current_page;
    public int from;
    public int last_page;
    public int per_page;
    @JsonProperty("to") 
    public int myto;
    public int total;
}
