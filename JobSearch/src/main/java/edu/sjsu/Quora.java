package edu.sjsu;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by HARMIT on 5/11/2015.
 */


@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "interestCourses")
public class Quora
{

    private String id;
    private String title;

    public String getId()
    {

        return id;
    }


    public String getTitle()
    {

        return title;
    }





}
