package edu.sjsu;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.linkedin.api.*;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;

@Repository
@Controller
public class LinkedInController {

    @Autowired
    CourseRepository courseRepository;
    
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

    @RequestMapping(value = "/",method=RequestMethod.GET)
    public String helloLinkedIn(Model model) {
        if (connectionRepository.findPrimaryConnection(LinkedIn.class) == null) {
            return "redirect:/connect/linkedin";
        }
        model.addAttribute(linkedIn.profileOperations().getUserProfile());
        List<LinkedInProfile> connections = linkedIn.connectionOperations().getConnections();
        model.addAttribute("connections", connections);

        return "hello";
    }
    
    @RequestMapping(value = "/technologies", method = RequestMethod.POST)
	public String technologies(@RequestBody Technologies technologies, Model model) throws RestClientException {
    	if (connectionRepository.findPrimaryConnection(LinkedIn.class) == null) {
            return "redirect:/connect/linkedin";
        }
    	
		ArrayList<Technology> items = technologies.getItems();
		technologyRepository.save(items);
		model.addAttribute("technologies", items);
		return "technologies";
	}

    @RequestMapping(value = "/courses",method=RequestMethod.GET)
    public String courses(Model model) throws RestClientException {
        if (connectionRepository.findPrimaryConnection(LinkedIn.class) == null) {
            return "redirect:/connect/linkedin";
        }

        RestTemplate restTemplate = new RestTemplate();
        Courses courses = restTemplate.getForObject("https://api.coursera.org/api/catalog.v1/courses", Courses.class);

        ArrayList<Course> elements = courses.getElements();

        courseRepository.save(elements);
        model.addAttribute("courses", elements);
        return "courses";
    }

    @RequestMapping(value = "/skillMatch",method=RequestMethod.GET)
    public String coursesMatch(Model model) {
        if (connectionRepository.findPrimaryConnection(LinkedIn.class) == null) {
            return "redirect:/connect/linkedin";
        }

        List<Course> elements = courseRepository.findAll();
        List<String> skills = linkedIn.profileOperations().getUserProfileFull().getSkills();

        ///cloudcomputing- Cloud Computing Concepts
        for(int i=0 ;i<elements.size();i++)
        {

            for(int j=0 ;j<skills.size();j++) {
                if (elements.get(i).getShortName().equalsIgnoreCase(skills.get(j))) {

                    model.addAttribute("skill", skills.get(j));
                    model.addAttribute("course", elements.get(i).getShortName());
                    model.addAttribute("name",elements.get(i).getName());

                }
            }

        }

        /*RestTemplate restTemplate = new RestTemplate();
        Courses courses = restTemplate.getForObject("https://api.coursera.org/api/catalog.v1/courses", Courses.class);
        ArrayList<Course> elements = courses.getElements();

        cr.save(elements);
        model.addAttribute("courses", elements);*/

        return "skillMatch";
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


}
