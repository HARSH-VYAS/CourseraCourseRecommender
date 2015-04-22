package edu.sjsu;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.mongodb.core.mapping.Document;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "courses")
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
