package edu.sjsu;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "technologies")
public class Technology {
	@Id
	private String id;

	private boolean has_synonyms;
	private boolean is_moderator_only;
	private boolean is_required;
	private int count;
	private String name;
	
	public String getId() {
        return id;
    }
	
	public String getName() {
        return name;
    }
	
	public int getCount() {
        return count;
    }
	
	public boolean getHas_synonyms() {
        return has_synonyms;
    }
	
	public boolean getIs_moderator_only() {
        return is_moderator_only;
    }
	
	public boolean getIs_required() {
        return is_required;
    }  
}