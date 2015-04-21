package edu.sjsu;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Course {
	private int id;
	private String shortName;
	private String name;
	
	public int getId() {
        return id;
    }
	
	public String getShortName() {
        return shortName;
    }

    public String getName() {
        return name;
    }   
}
