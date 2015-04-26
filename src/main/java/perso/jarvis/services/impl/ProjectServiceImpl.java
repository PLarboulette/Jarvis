package perso.jarvis.services.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import perso.jarvis.beans.Project;
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

        Map<String, String> userProperties = Redis.getHash("User : "+id);
        String fieldUserProjects = userProperties.get("userProjects");
        List<String> listIDsProject = Redis.getList(fieldUserProjects);
        ArrayList<String> keysProject = new ArrayList<>();

        listIDsProject.stream().filter(keyProject -> !keysProject.contains(keyProject)).forEach(keyProject -> {
            keysProject.add(keyProject);
            Project projectTemp = Redis.getProjectFromID(keyProject);
            result.add(projectTemp);
        });
        return result;
    }

    /**
     * State : OK (01/04/2015)
     * @param project project to save in database
     */
    public void createProject(Project project, String idUser) {
        HashMap<String,String> projectProperties = new HashMap<>();
        projectProperties.put("projectId", String.valueOf(project.hashCode()));
        projectProperties.put("projectTasks", "Tasks : " + String.valueOf(project.hashCode()));
        projectProperties.put("projectTechnologies", project.getTechnologies());
        addProjectForUser(String.valueOf(project.hashCode()), idUser);
        Redis.insertHash("Project", String.valueOf(project.hashCode()), projectProperties);

        DBObject projectForDB = new BasicDBObject();
        projectForDB.put("name",project.getName());
        projectForDB.put("description",project.getDescription());
        projectForDB.put("beginDate",project.getBeginDate().toDate());
        projectForDB.put("endDate",project.getEndDate().toDate());
        projectForDB.put("achieved",false);



    }

    /**
     *
     * State : ??
     * @param projectId project's id to add to the user's list of projects
     * @param userId user's id
     */
    public void addProjectForUser(String projectId, String userId) {
        Redis.insertList("Projects",userId,projectId);
    }

    public Project getProject(String idProject) {
        return Redis.getProjectFromID(idProject);
    }

    @Override
    public void updateProject(String projectID, Project project) {
        Redis.setValueToHash("Project : " + projectID, "projectName", project.getName());
        Redis.setValueToHash("Project : " + projectID, "projectDescription", project.getDescription());
        Redis.setValueToHash("Project : " + projectID, "projectBeginDate", project.getBeginDate());
        Redis.setValueToHash("Project : " + projectID, "projectEndDate", project.getEndDate());
        Redis.setValueToHash("Project : " + projectID, "projectTechnologies", project.getTechnologies());
        Redis.setValueToHash("Project : " + projectID, "projectAchieved", project.getAchieved());
    }
}
