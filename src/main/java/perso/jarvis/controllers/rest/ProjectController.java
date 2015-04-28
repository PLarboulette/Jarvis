package perso.jarvis.controllers.rest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import perso.jarvis.beans.Project;
import perso.jarvis.services.ProjectService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Pierre on 31/03/2015.
 */
@Controller
public class ProjectController {

    private final Logger logger = Logger.getLogger(ProjectController.class);

    @Resource
    ProjectService projectService;

    /**
     * Create a project for an user
     *
     * @param project
     * @param request
     * @param httpServletResponse
     */
    @RequestMapping(value = "user/{userId}/project", method = RequestMethod.POST)
    public void createProject(@PathVariable("userId") String userId, @RequestBody Project project, HttpServletRequest request, HttpServletResponse httpServletResponse) {
        logger.info("createProject");
        // projectService.createProject(project, userId);
    }


    /**
     * @param userId
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/user/{userId}/project", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Project> getProjects(@PathVariable String userId, HttpServletRequest request, HttpServletResponse response) {

        logger.info("getProjects");
        userId = "Login";
        List<Project> result = projectService.getProjects(userId);

        logger.info("NB projects :" + result.size());

        return result;
    }

    @RequestMapping(value = "/user/userID/project/{idProject}", method = RequestMethod.GET)
    public
    @ResponseBody
    Project getProject(@PathVariable("idProject") String idProject, HttpServletRequest request, HttpServletResponse response) {
        String projectID = request.getParameter("projectID");
        return projectService.getProject(idProject);
    }

    @RequestMapping(value = "/user/userID/project/{projectID}", method = RequestMethod.POST)
    public
    @ResponseBody
    void updateProject(@PathVariable("projectID") String projectID, @RequestBody Project project, HttpServletRequest request, HttpServletResponse response) {
        projectService.updateProject(projectID, project);
    }

   /* *//**
     *
     * @param id user's id
     * @param project the project to add
     * @param request
     * @param httpServletResponse
     *//*
    @RequestMapping(value="/user/addProjectForUser", method= RequestMethod.POST)
    public @ResponseBody void addProjectForUser (HttpServletRequest request, HttpServletResponse httpServletResponse) {
        logger.info("createUser");
        String userId = request.getParameter("userId");
        String projectId = request.getParameter("projectId");

       projectService.ad dProjectForUser(projectId, userId);
    }*/

}
