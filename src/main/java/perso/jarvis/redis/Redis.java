package perso.jarvis.redis;

import perso.jarvis.beans.Project;
import perso.jarvis.beans.Task;
import perso.jarvis.beans.User;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.*;

/**
 * Created by Pierre on 31/03/2015.
 */
public class Redis implements ServletContextListener {

    private static Jedis jedis;


    public void contextInitialized(ServletContextEvent sce) {
        jedis = new Jedis("localhost");

    }

    public void contextDestroyed(ServletContextEvent sce) {
        jedis.close();
    }

    /**
     * State : OK (01/04/2015)
     * @param type User, Project, Task or Training
     * @param id id of the object
     * @param properties Map of all the attributes of the object
     */
    public static void insertHash ( String type, String id, Map<String,String> properties) {
        jedis.hmset(type + " : " + id, properties);
    }

    /**
     * State : OK (01/04/2015)
     * @param type Users, Projects, Tasks or Trainings
     * @param idKey if of the object
     * @param idValue value to add to the list
     */
    public static void insertList (String type, String idKey, String idValue) {
        jedis.lpush(type + " : " + idKey, idValue);
    }

    /**
     * State : ??
     * @param type User, Project, Task or Training
     * @param id id of the object to delete
     */
    public static void deleteKey (String type, String id) {
        jedis.del(type + " : " + id);
    }


    /**
     * State : OK (01/04/2015)
     * @param type User, Project, Task or Training
     * @return a Set of all the objects of the given type stored in database
     */
    public static Set getDatas (String type) {
        Set result = jedis.keys(type + " : *");
        return result;
    }

    public static  Map<String,String> getHash (String id) {
        Map<String,String> result = jedis.hgetAll(id);
        return result;
    }

    /**
     * State : OK (01/04/2015)
     * @param id && Id of the list
     * @return a list of ID
     */
    public static List<String> getList (String id) {
        return jedis.lrange(id,0,1000);
    }


    /**
     * State : ??
     * @param id User : ID
     * @return user with all of his projects and tasks
     */
    public static User getUserFromID (String id) {
        User user = new User();
        Map<String,String> userProperties = jedis.hgetAll(id);
        for (String key : userProperties.keySet()) {
            switch(key) {
                case "userId" :
                    user.setId(userProperties.get(key));
                    break;
                case "userLastName" :
                    user.setLastName(userProperties.get(key));
                    break;
                case "userFirstName" :
                    user.setFirstName(userProperties.get(key));
                    break;
                case "userLogin" :
                    user.setLogin(userProperties.get(key));
                    break;
                case "userPassword" :
                    user.setPassword(userProperties.get(key));
                    break;
                case "userProjects" :
                    List<String> listProjects = Redis.getList(userProperties.get(key));
                    ArrayList<Project> listProjectsForUser = new ArrayList<Project>();
                    for (String idProject : listProjects) {
                        Project projectTemp = getProjectFromID(idProject);
                        listProjectsForUser.add(projectTemp);
                    }
                    user.setListProjects(listProjectsForUser);
                    break;
            }
        }
        return user;
    }


    /**
     * State : ??
     * @param id -- Project : ID
     * @return
     */
    public static Project getProjectFromID (String id) {
        Project project = new Project();
        Map<String,String> projectProperties = jedis.hgetAll("Project : "+id);
        for (String key : projectProperties.keySet()) {
            switch(key) {
                case "projectId" :
                    project.setId(projectProperties.get(key));
                    break;
                case "projectDescription" :
                    project.setDescription(projectProperties.get(key));
                    break;
                case "projectName" :
                    project.setName(projectProperties.get(key));
                    break;
                case "projectEndDate" :
                    project.setEndDate(projectProperties.get(key));
                    break;
                case "projectBeginDate" :
                    project.setBeginDate(projectProperties.get(key));
                    break;
                case "projectTechnologies" :
                    String[] technologiesFromDB = projectProperties.get(key).split(";");
                    ArrayList<String> technologies = new ArrayList<>();
                    for (int i = 0 ; i< technologiesFromDB.length ; i++) {
                        technologies.add(technologiesFromDB[i]);
                    }
                    project.setTechnologies(technologies);
                    break;
                case "projectAchieved" :
                    project.setAchieved(projectProperties.get(key));
                    break;
                case "projectTasks" :
                    List<String> listIDTasks = Redis.getList(projectProperties.get(key));
                    ArrayList<Task> listTasksForProject = new ArrayList<Task>();
                    for (String idTask : listIDTasks) {
                        Task taskTemp = getTaskFromID(idTask);
                        listTasksForProject.add(taskTemp);
                    }
                    project.setListTasks(listTasksForProject);
                    break;
            }
        }
        return project;
    }

    /**
     * State : ??
     * @param id -- Task : ID
     * @return
     */
    public static Task getTaskFromID (String id) {

        Task task = new Task();
        Map<String,String> taskProperties = jedis.hgetAll(id);
        for (String key : taskProperties.keySet()) {
            switch (key) {
                case "taskId" :
                    task.setId(taskProperties.get(key));
                    break;
                case "taskName" :
                    task.setName(taskProperties.get(key));
                    break;
                case "taskText" :
                    task.setText(taskProperties.get(key));
                    break;
                case "taskBeginDate" :
                    task.setBeginDate(taskProperties.get(key));
                    break;
                case "taskEndDate" :
                    task.setEndDate(taskProperties.get(key));
                    break;
                case "taskDuration" :
                    task.setDuration(taskProperties.get(key));
                    break;
            }
        }
        return task;
    }

    public static void setValueToHash (String id, String key, String value){
        jedis.hset(id,key,value);
    }



}
