package perso.jarvis.services.impl;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;
import perso.jarvis.beans.Project;
import perso.jarvis.mongo.Mongo;
import perso.jarvis.redis.Redis;
import perso.jarvis.services.ProjectService;

import java.util.*;

/**
 * Created by Pierre on 31/03/2015.
 */
public class ProjectServiceImpl implements ProjectService {


    /**
     * State : OK (01/04/2015)
     * @param id = the user's id
     * @return = List of the user's project
     */
    public List<Project> getProjects(String id) {
        List<Project> result = new ArrayList<>();

       /* Map<String, String> userProperties = Redis.getHash("User : "+id);
        String fieldUserProjects = userProperties.get("userProjects");
        List<String> listIDsProject = Redis.getList(fieldUserProjects);
        ArrayList<String> keysProject = new ArrayList<>();

        listIDsProject.stream().filter(keyProject -> !keysProject.contains(keyProject)).forEach(keyProject -> {
            keysProject.add(keyProject);
            Project projectTemp = Redis.getProjectFromID(keyProject);
            result.add(projectTemp);
        });*/
        return result;
    }

    /**
     * State : OK (01/04/2015)
     * @param project project to save in database
     */
    public void createProject(Project project, String idUser) {

        DBObject projectForDB = new BasicDBObject();

        String project_id = UUID.randomUUID().toString();
        projectForDB.put("project_id", project_id);
        String project_name = project.getName();
        projectForDB.put("name",project_name);
        projectForDB.put("description",project.getDescription());
        projectForDB.put("beginDate",project.getBeginDate());
        projectForDB.put("endDate",project.getEndDate());
        projectForDB.put("achieved",false);

        addProjectForUser(project_id,project_name,idUser);

        // Technologies à faire

        Mongo.insert(projectForDB,"projects");



    }


    public void addProjectForUser(String project_id, String project_name, String userId) {
        List<DBObject> listUsers = Mongo.find("users",new BasicDBObject("login",userId));
        DBObject user = listUsers.get(0);
        BasicDBList projects = (BasicDBList) user.get("projects");
        BasicDBObject project = new BasicDBObject("name", project_name).append("project_id",project_id);
        projects.add(project);
        Mongo.save("users",user);
    }

    public Project getProject(String idProject) {
        return Redis.getProjectFromID(idProject);
    }

    @Override
    public void updateProject(String projectID, Project project) {
        Redis.setValueToHash("Project : " + projectID, "projectName", project.getName());
        Redis.setValueToHash("Project : " + projectID, "projectDescription", project.getDescription());
        //Redis.setValueToHash("Project : " + projectID, "projectBeginDate", project.getBeginDate());
       //  Redis.setValueToHash("Project : " + projectID, "projectEndDate", project.getEndDate());
        Redis.setValueToHash("Project : " + projectID, "projectTechnologies", project.getTechnologies());
        Redis.setValueToHash("Project : " + projectID, "projectAchieved", project.getAchieved());
    }
}
