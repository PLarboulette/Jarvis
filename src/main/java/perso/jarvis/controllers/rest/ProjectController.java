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
     *
     * @param request
     * @param httpServletResponse
     */
    @RequestMapping(value="/projects", method= RequestMethod.POST)
    public void createProject (HttpServletRequest request, HttpServletResponse httpServletResponse) {
        logger.info("createProject");

        BufferedReader br = null;
        String json = "";

        try {
            br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            if(br != null){
                json = br.readLine();
            }
            ObjectMapper mapper = new ObjectMapper();
            Project project = mapper.readValue(json, Project.class);
            projectService.createProject(project);

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    /**
     *
     * @param id user's id
     * @param project the project to add
     * @param request
     * @param httpServletResponse
     */
    @RequestMapping(value="/user/addProjectForUser", method= RequestMethod.POST)
    public @ResponseBody void addProjectForUser (HttpServletRequest request, HttpServletResponse httpServletResponse) {
        logger.info("createUser");
        String userId = request.getParameter("userId");
        String projectId = request.getParameter("projectId");

        projectService.addProjectForUser(projectId, userId);
    }


    /**
     *
     * @param id user's id
     * @param request
     * @param response
     */
    @RequestMapping(value="/user/getProjects", method= RequestMethod.GET)
    public @ResponseBody
    List<Project> getProjects (HttpServletRequest request, HttpServletResponse response) {

        logger.info("getProjects");
        String userId = request.getParameter("userId");
        userId="Pierre";

        List<Project> result = projectService.getProjects(userId);
        System.out.println(result.size());



        return result;
    }






}
