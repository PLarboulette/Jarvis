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

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Redis.class);

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

// --Commented out by Inspection START (24/04/2015 14:10):
//    /**
//     * State : ??
//     * @param type User, Project, Task or Training
//     * @param id id of the object to delete
//     */
//    public static void deleteKey (String type, String id) {
//        jedis.del(type + " : " + id);
//    }
// --Commented out by Inspection STOP (24/04/2015 14:10)


    /**
     * State : OK (01/04/2015)
     * @return a Set of all the objects of the given type stored in database
     */
    public static Set getDatas() {
        return jedis.keys("User" + " : *");
    }

    public static  Map<String,String> getHash (String id) {
        return jedis.hgetAll(id);
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
                    ArrayList<Project> listProjectsForUser = new ArrayList<>();
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
        /*Project project = new Project();
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
                    project.setTechnologies(projectProperties.get(key));
                    break;
                case "projectAchieved" :
                    project.setAchieved(projectProperties.get(key));
                    break;
                case "projectTasks" :
                    List<String> listIDTasks = Redis.getList(projectProperties.get(key));
                    ArrayList<Task> listTasksForProject = new ArrayList<>();
                    ArrayList<String> keysTask = new ArrayList<>();
                    listIDTasks.stream().filter(idTask -> !keysTask.contains(idTask)).forEach(idTask -> {
                        keysTask.add(idTask);
                        Task taskTemp = getTaskFromID(idTask);
                        listTasksForProject.add(taskTemp);
                    });

                    logger.info(listTasksForProject.size());

                    project.setListTasks(listTasksForProject);
                    break;
            }
        }*/
      // return project;
        return new Project();
    }

    /**
     * State : ??
     * @param id -- Task : ID
     * @return
     */
    private static Task getTaskFromID(String id) {

        Task task = new Task();
        Map<String,String> taskProperties = jedis.hgetAll("Task : "+id);
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
                case "taskAchieved" :
                    task.setAchieved(taskProperties.get(key));
                    break;
            }
        }
        return task;
    }

    public static void setValueToHash (String id, String key, String value){
        jedis.hset(id,key,value);
    }



}
