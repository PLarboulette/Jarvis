package perso.jarvis.services;

import perso.jarvis.beans.Task;

/**
 * Created by Pierre on 31/03/2015.
 */
public interface TaskService {

    // State OK (01/04/2015)
     void createTask(Task task);

    void addTaskForProject(String taskId, String projectId);

}
