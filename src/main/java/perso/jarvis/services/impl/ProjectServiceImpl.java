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
    public List<Project> getProjects(String userId) {

        List<Project> result = new ArrayList<>();

        DBObject user = Mongo.find("users",new BasicDBObject("login",userId)).get(0);
        BasicDBList projectsFieldForUser = (BasicDBList) user.get("projects");
        ArrayList<String> projectIds = new ArrayList<>();
        for (String id  : projectsFieldForUser.keySet()) {
            DBObject project = (DBObject) projectsFieldForUser.get(id);
            projectIds.add(project.get("project_id").toString());
        }

        for (String s : projectIds) {
            List<DBObject> projectsFromDB = Mongo.find("projects", new BasicDBObject("_id",s));
            DBObject projectFromDB;
            Project project;
            if (projectsFromDB.size() != 0 ) {
                projectFromDB = projectsFromDB.get(0);
                project = new Project();
                project.setName(projectFromDB.get("name").toString());
                project.setDescription(projectFromDB.get("description").toString());
                project.setBeginDate(projectFromDB.get("beginDate").toString());
                project.setEndDate(projectFromDB.get("endDate").toString());
                project.setTechnologies(projectFromDB.get("technologies").toString());
                result.add(project);
            }
        }
        return result;
    }

    /**
     * State : OK (01/04/2015)
     * @param project project to save in database
     */
    public void createProject(Project project, String idUser) {

        DBObject projectForDB = new BasicDBObject();

        String project_id = UUID.randomUUID().toString();
        projectForDB.put("_id", project_id);
        String project_name = project.getName();
        projectForDB.put("name",project_name);
        projectForDB.put("description",project.getDescription());
        projectForDB.put("beginDate",project.getBeginDate());
        projectForDB.put("endDate",project.getEndDate());
        projectForDB.put("achieved",false);
        projectForDB.put("technologies",project.getTechnologies());


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
