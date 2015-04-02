package perso.jarvis.beans;

import org.joda.time.DateTime;

import java.util.ArrayList;

/**
 * Created by Pierre on 30/03/2015.
 */
public class Project {

    private String id;
    private String name;
    private String beginDate;
    private String endDate;
    private ArrayList<Task> listTasks;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public ArrayList<Task> getListTasks() {
        return listTasks;
    }

    public void setListTasks(ArrayList<Task> listTasks) {
        this.listTasks = listTasks;
    }
}
