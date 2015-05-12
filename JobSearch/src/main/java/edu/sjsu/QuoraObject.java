package edu.sjsu;

/**
 * Created by HARMIT on 5/11/2015.
 */
public class QuoraObject
{

    public Quora QuoraObjectList = new Quora();

    private String id;
    private String title;


    public Quora getQuoraObjectList() {
        return QuoraObjectList;
    }

    public void setQuoraObjectList(Quora quoraObjectList) {
        QuoraObjectList = quoraObjectList;
    }


    public String getId()
    {

        return id;
    }


    public String getTitle()
    {

        return title;
    }
}
