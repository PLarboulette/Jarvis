package perso.jarvis.services;

import perso.jarvis.beans.Project;
import perso.jarvis.beans.User;

import java.util.List;

/**
 * Created by Pierre on 31/03/2015.
 */
public interface ProjectService {


    // State OK (01/04/2015)
    void createProject(Project project, String idUser);

    void addProjectForUser (String projectId, String userId);

    List<Project> getProjects(String id);

    Project getProject(String idProject);

    void updateProject(Project project, String idUser);



}
