package edu.sjsu;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by harsh on 4/17/2015.
 */

@JsonIgnoreProperties(ignoreUnknown =true)
public class Coursera{

 String name;

    public String getName() {
        return name;
    }

    public void setName(String courses) {
        this.name = courses;
    }

    public Coursera(String courses) {
        this.name = courses;

    }
}
