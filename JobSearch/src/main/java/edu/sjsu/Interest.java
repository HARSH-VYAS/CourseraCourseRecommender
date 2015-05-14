package edu.sjsu;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "interests")
public class Interest {

    @Id
    private int id;
	private String name;
	
	public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
