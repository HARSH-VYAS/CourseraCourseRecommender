package edu.sjsu;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        
       /* RestTemplate restTemplate = new RestTemplate();
        Courses courses = restTemplate.getForObject("https://api.coursera.org/api/catalog.v1/courses", Courses.class);
        ArrayList<Course> elements = courses.getElements();
        for (Course course: elements) {
        	System.out.println("Id:    " + course.getId());
        	System.out.println("Name:    " + course.getShortName());
        	System.out.println("Name:    " + course.getName());
        }*/
    }
} 