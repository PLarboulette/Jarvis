package perso.jarvis.services;

import perso.jarvis.beans.Project;

import java.util.List;

/**
 * Created by Pierre on 31/03/2015.
 */
public interface ProjectService {


    void createProject(Project project, String userId);

    List<Project> getProjects(String id);

    Project getProject(String idProject);

    void updateProject(String projectId, Project project);



}
