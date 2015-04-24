package perso.jarvis.beans;

import java.util.ArrayList;

/**
 * Created by Pierre on 30/03/2015.
 */
public class Project {

    private String id;
    private String name;
    private String description;
    private String beginDate;
    private String endDate;
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

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
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
