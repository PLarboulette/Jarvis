package perso.jarvis.beans;

import org.joda.time.DateTime;

import java.util.ArrayList;

/**
 * Created by Pierre on 30/03/2015.
 */
public class User {


    private String id;
    private String login;
    private String password;
    private String lastName;
    private String firstName;
    private String beginDate;
    private String endDate;
    private ArrayList<Project> listProjects;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public ArrayList<Project> getListProjects() {
        return listProjects;
    }

    public void setListProjects(ArrayList<Project> listProjects) {
        this.listProjects = listProjects;
    }
}


