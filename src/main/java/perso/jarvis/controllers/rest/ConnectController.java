package perso.jarvis.controllers.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import perso.jarvis.beans.User;
import perso.jarvis.redis.Redis;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Pierre on 31/03/2015.
 */
@Controller
public class ConnectController {

    @RequestMapping(value="/connect",method= RequestMethod.GET)
    public void connect (HttpServletRequest request, HttpServletResponse response ) {

        String login = request.getParameter(("login"));
        String password = request.getParameter("password");

        List<User> listUsers = new ArrayList<User>();

        User userConnected = null;

        Set users = Redis.getDatas("User");
        Iterator i = users.iterator();
        while (i.hasNext()) {
            String idUser = i.next().toString();
            User user = Redis.getUserFromID(idUser);
            listUsers .add(user);

        }

        for (User user : listUsers) {
            if (user.getLogin().equals(login)) {
                if (user.getPassword().equals(password)) {
                        userConnected = user;
                    break;
                }
            }
        }

        try {
            if (userConnected != null) {
            //response.getWriter().write(userConnected.getId());
                response.sendRedirect("/Jarvis/pages/index.html");
                System.out.println("OK redirection");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



}
