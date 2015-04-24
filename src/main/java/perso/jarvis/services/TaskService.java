package perso.jarvis.services;

import perso.jarvis.beans.Task;

/**
 * Created by Pierre on 31/03/2015.
 */
public interface TaskService {

    // State OK (01/04/2015)
     void createTask(String projectID, Task task);

    // --Commented out by Inspection (24/04/2015 14:10):void addTaskForProject(String taskId, String projectId);

}
