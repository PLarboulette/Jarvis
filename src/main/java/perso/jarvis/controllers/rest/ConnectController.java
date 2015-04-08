package perso.jarvis.controllers.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import perso.jarvis.beans.User;
import perso.jarvis.redis.Redis;
import perso.jarvis.services.ConnectService;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by Pierre on 31/03/2015.
 */
@Controller
public class ConnectController {

    @Resource
    ConnectService connectService;


    private Logger logger = Logger.getLogger(ConnectController.class);

    @RequestMapping( method=RequestMethod.POST, value="/connect")
    public @ResponseBody
    void connect(@RequestBody User user, HttpServletRequest request, HttpServletResponse httpServletResponse) {
        logger.info("Connexion à l'application");
        String login = user.getLogin();
        String password = user.getPassword();

        boolean authorized = connectService.checkLogin(login,password);

        if (authorized) {
            String token = UUID.randomUUID().toString();
            request.getSession(true).setAttribute("token",token);;
            request.getSession(true).setAttribute("user",user);
            connectService.addToken(token,user);
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }







    }



}
