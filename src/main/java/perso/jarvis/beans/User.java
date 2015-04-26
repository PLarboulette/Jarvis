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
    private DateTime creationDate;
    private ArrayList<Project> listProjects;

public User() {

}
// --Commented out by Inspection START (24/04/2015 14:10):
//    public String getId() {
//        return id;
//    }
// --Commented out by Inspection STOP (24/04/2015 14:10)

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



    public void setListProjects(ArrayList<Project> listProjects) {
        this.listProjects = listProjects;
    }

    public DateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(DateTime creationDate) {
        this.creationDate = creationDate;
    }
}


