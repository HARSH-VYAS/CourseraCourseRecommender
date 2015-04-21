package edu.sjsu;

import java.util.List;

import javax.inject.Inject;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.linkedin.api.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;

@Controller
public class LinkedInController {

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

        List<String> skills = linkedIn.profileOperations().getUserProfileFull().getSkills();
        model.addAttribute("Skills",skills);

        return "hello";
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
