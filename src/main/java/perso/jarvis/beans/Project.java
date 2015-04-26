package perso.jarvis.beans;

import org.joda.time.DateTime;

import java.util.ArrayList;

/**
 * Created by Pierre on 30/03/2015.
 */
public class Project {

    private String id;
    private String name;
    private String description;
    private DateTime beginDate;
    private DateTime endDate;
    private ArrayList<Task> listTasks;
    private String achieved;
    private String technologies;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DateTime getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(DateTime beginDate) {
        this.beginDate = beginDate;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }


    public void setListTasks(ArrayList<Task> listTasks) {
        this.listTasks = listTasks;
    }

    public String getAchieved() {
        return achieved;
    }

    public void setAchieved(String achieved) {
        this.achieved = achieved;
    }

    public String getTechnologies() {
        return technologies;
    }

    public void setTechnologies(String technologies) {
        this.technologies = technologies;
    }
}
