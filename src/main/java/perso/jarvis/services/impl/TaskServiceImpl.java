package perso.jarvis.services.impl;

import perso.jarvis.beans.Task;
import perso.jarvis.redis.Redis;
import perso.jarvis.services.TaskService;

import java.util.HashMap;

/**
 * Created by Pierre on 31/03/2015.
 */
public class TaskServiceImpl implements TaskService {

    /**
     * State : OK (01/04/2015)
     * @param task task to save in database
     */
    public void createTask(String projectID, Task task) {
        HashMap<String,String> taskProperties = new HashMap<>();
        taskProperties.put("taskId",String.valueOf(task.hashCode()));
        taskProperties.put("taskName",task.getName());
        taskProperties.put("taskText",task.getText());
        taskProperties.put("taskBeginDate",task.getBeginDate());
        taskProperties.put("taskEndDate",task.getEndDate());
        taskProperties.put("taskDuration",task.getDuration());
        taskProperties.put("taskAchieved","false");
        Redis.insertHash("Task", String.valueOf(task.hashCode()), taskProperties);
        addTaskForProject(String.valueOf(task.hashCode()), projectID);
    }

    /**
     * State : ??
     * @param taskId the task's id to add to the list of tasks of the project
     * @param projectId the project's id
     */
    public void addTaskForProject(String taskId, String projectId) {
        Redis.insertList("Tasks",projectId,taskId);
    }
}
