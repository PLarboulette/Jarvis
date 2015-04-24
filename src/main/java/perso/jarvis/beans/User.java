package perso.jarvis.beans;


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
    private String accessToScrumboard;

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

// --Commented out by Inspection START (24/04/2015 14:10):
//    public String getBeginDate() {
//        return beginDate;
//    }
// --Commented out by Inspection STOP (24/04/2015 14:10)

// --Commented out by Inspection START (24/04/2015 14:10):
//    public void setBeginDate(String beginDate) {
//        this.beginDate = beginDate;
//    }
// --Commented out by Inspection STOP (24/04/2015 14:10)

// --Commented out by Inspection START (24/04/2015 14:10):
//    public String getEndDate() {
//        return endDate;
//    }
// --Commented out by Inspection STOP (24/04/2015 14:10)

// --Commented out by Inspection START (24/04/2015 14:10):
//    public void setEndDate(String endDate) {
//        this.endDate = endDate;
//    }
// --Commented out by Inspection STOP (24/04/2015 14:10)

// --Commented out by Inspection START (24/04/2015 14:10):
//    public ArrayList<Project> getListProjects() {
//        return listProjects;
//    }
// --Commented out by Inspection STOP (24/04/2015 14:10)

    public void setListProjects(ArrayList<Project> listProjects) {
        this.listProjects = listProjects;
    }


// --Commented out by Inspection START (24/04/2015 14:10):
//    public String getAccessToScrumboard() {
//        return accessToScrumboard;
//    }
// --Commented out by Inspection STOP (24/04/2015 14:10)

// --Commented out by Inspection START (24/04/2015 14:10):
//    public void setAccessToScrumboard(String accessToScrumboard) {
//        this.accessToScrumboard = accessToScrumboard;
//    }
// --Commented out by Inspection STOP (24/04/2015 14:10)
}


