package edu.sjsu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.linkedin.api.*;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;




@Repository
@Controller
public class LinkedInController {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    QuoraRepository quoraRepository;
    @Autowired
    TechnologyRepository technologyRepository;

    private LinkedIn linkedIn;

    private ConnectionRepository connectionRepository;
    RestTemplate restTemplate = new RestTemplate();



    @Inject
    public LinkedInController(LinkedIn linkedIn, ConnectionRepository connectionRepository) {
        this.linkedIn = linkedIn;
        this.connectionRepository = connectionRepository;
    }

    @RequestMapping(value ="/",method=RequestMethod.GET)
    public String helloLinkedIn(Model model) {
        if (connectionRepository.findPrimaryConnection(LinkedIn.class) == null) {
            return "redirect:/connect/linkedin";
        }
        model.addAttribute(linkedIn.profileOperations().getUserProfile());
        List<LinkedInProfile> connections = linkedIn.connectionOperations().getConnections();
        model.addAttribute("connections", connections);

        return "hello";
    }

    @RequestMapping(value ="/technologies", method = RequestMethod.POST)
	public void technologies(@RequestBody Technologies technologies, Model model)throws RestClientException {    	
		ArrayList<Technology> items = technologies.getItems();
		technologyRepository.save(items);
	}

    @RequestMapping(value ="/courses",method=RequestMethod.POST)
    public void courses(@RequestBody Courses courses) throws RestClientException {      
        ArrayList<Course> elements = courses.getElements();
        ArrayList<String> courseList = new ArrayList<String>();

        courseRepository.save(elements);

        for (int i = 0; i <elements.size() ; i++) {
            courseList.add(elements.get(i).getName());
        }
    }

    @RequestMapping(value ="/interestCourses",method=RequestMethod.POST)
    public ResponseEntity Quora(@RequestBody Courses interests) throws RestClientException {
        RestTemplate restTemplate = new RestTemplate();

        QuoraContent quoraContent = restTemplate.getForObject("http://quora-api.herokuapp.com//users/Harmit-Patel-1/activity", QuoraContent.class);

        HashMap<String,String > result = new HashMap<String,String>();
        ArrayList<Quora> q = quoraContent.getActivity();
        ArrayList<String> q1 = new ArrayList<String>();

        List<Course> courses = courseRepository.findAll();
        
        for (int i = 0; i <q.size() ; i++) {
            String id= q.get(i).getId();
            String title =q.get(i).getTitle();
            if(id.charAt(0)=='3') {
                System.out.println("printing titles   " + i);
                for (int j = 0; j < courses.size(); j++) {
                    if(courses.get(j).getShortName().contains(title.toLowerCase())) {
                        result.put(title,courses.get(j).getName());
                        System.out.println("-------------------;  " + j + "``````````````````` "  +id + " " + title);
                    }
                }
            }
        }
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/suggestedCourses",method=RequestMethod.GET)
    public ResponseEntity coursesMatch(Model model) {  
        HashMap<String,String> suggestedCourses = new HashMap<String, String>();

        List<Technology> elements = technologyRepository.findAll();
        List<Course> courses = courseRepository.findAll();
        ArrayList<String>  course =  new ArrayList<String>();
        ArrayList<String>  technology =  new ArrayList<String>();

        for (int i=0 ;i<elements.size();i++ ) {
            for (int j = 0; j < courses.size(); j++) {

                String courseName = courses.get(j).getName().toLowerCase();
                if (courseName.substring(0,courseName.length()).contains(elements.get(i).getName())) {
                    if(!course.contains(courseName)) {
                        suggestedCourses.put(elements.get(i).getName().toLowerCase(),courseName);
                        course.add(courseName);
                    }
                }
            }
        }
        return new ResponseEntity(suggestedCourses, HttpStatus.OK);
    }

    @RequestMapping(value = "/jobs",method=RequestMethod.GET)
    public String joblistLinkedIn(Model model) {
        if (connectionRepository.findPrimaryConnection(LinkedIn.class) == null) {
            return "redirect:/connect/linkedin";
        }
        model.addAttribute(linkedIn.profileOperations().getUserProfile());
        List<Company> companyList = linkedIn.companyOperations().getFollowing();
        model.addAttribute("company",companyList);
        return "jobs";
    }

    @RequestMapping(value = "/jobsList",method=RequestMethod.GET)
    public String jobsPostedLinkedIn(Model model) {

       JobSearchParameters location = new JobSearchParameters();
        if (connectionRepository.findPrimaryConnection(LinkedIn.class) == null) {
            return "redirect:/connect/linkedin";
        }
        Jobs jobs= linkedIn.jobOperations().searchJobs(location);
        List<Job> jobList= jobs.getJobs();
        model.addAttribute("JobSearch",jobList);
        model.addAttribute(linkedIn.profileOperations().getUserProfile());

        return "jobsList";
    }

    @Scheduled(fixedRate = 5000)
    public void sendMail(){




    }


}
