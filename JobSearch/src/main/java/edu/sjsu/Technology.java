package edu.sjsu;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.mongodb.core.mapping.Document;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "technologies")
public class Technology {
	private boolean has_synonyms;
	private boolean is_moderator_only;
	private boolean is_required;
	private int count;
	private String name;
	
	public void setHas_synonyms(boolean has_synonyms) {
		this.has_synonyms = has_synonyms;
	}

	public void setIs_moderator_only(boolean is_moderator_only) {
		this.is_moderator_only = is_moderator_only;
	}

	public void setIs_required(boolean is_required) {
		this.is_required = is_required;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setName(String name) {
		this.name = name;
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
	
	public int getCount() {
        return count;
    }

    public String getName() {
        return name;
    }

}
