package com.nighthawk.spring_portfolio.mvc.soccer;

public class Team {
    
    String name = "";
    String country = "";
    String logo = "";

    public String getCountry() {
        return country;
    }

    public String getLogo() {
        return logo;
    }

    public String getName() {
        return name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Name: " + name + " (" + country + ") " + " Logo: " + logo;
    }
}
