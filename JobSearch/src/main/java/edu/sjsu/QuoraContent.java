package edu.sjsu;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by HARMIT on 5/11/2015.
 */
public class QuoraContent
{


    public ArrayList<Quora> activity = new ArrayList<Quora>();
    public Date last_updated = new Date();
    public String username = new String();

    public Date getLast_updated() {
        return last_updated;
    }

    public String getUsername() {
        return username;
    }



    public ArrayList<Quora> getActivity() {
        return activity;
    }




}
