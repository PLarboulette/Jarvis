package perso.jarvis.controllers.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import perso.jarvis.beans.Project;
import perso.jarvis.beans.User;
import perso.jarvis.services.ProjectService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by Pierre on 31/03/2015.
 */
@Controller
public class ProjectController {

    Logger logger = Logger.getLogger(ProjectController.class);

    @Resource
    ProjectService projectService;

    /**
     * Create a project for an user
     * @param idUser
     * @param project
     * @param request
     * @param httpServletResponse
     */
    @RequestMapping(value="user/{userID}/project", method= RequestMethod.POST)
    public void createProject (@PathVariable("userID") String idUser, @RequestBody Project project, HttpServletRequest request, HttpServletResponse httpServletResponse) {
        logger.info("createProject");
        projectService.createProject(project,"Login");
    }


    /**
     *
     * @param userId
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/user/{userID}/project", method= RequestMethod.GET)
    public @ResponseBody
    List<Project> getProjects (@PathVariable("userID") String userId, HttpServletRequest request, HttpServletResponse response) {

        logger.info("getProjects");
        userId="Login";
        List<Project> result = projectService.getProjects(userId);
        return result;
    }

    @RequestMapping(value="/user/userID/project/{idProject}",method=RequestMethod.GET)
    public @ResponseBody Project getProject (@PathVariable("idProject") String idProject, HttpServletRequest request, HttpServletResponse response) {
        String projectID = request.getParameter("projectID");
        return projectService.getProject(idProject);
    }

    @RequestMapping(value="/user/userID/project", method=RequestMethod.PUT)
    public @ResponseBody void updateProject (@RequestBody Project project,HttpServletRequest request, HttpServletResponse response) {

        System.out.println(project.getTechnologies());


        projectService.updateProject(project, "Login");
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
