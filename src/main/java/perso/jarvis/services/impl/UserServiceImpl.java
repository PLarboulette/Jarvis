package perso.jarvis.services.impl;

import org.apache.log4j.Logger;
import perso.jarvis.beans.User;
import perso.jarvis.redis.Redis;
import perso.jarvis.services.UserService;

import java.util.*;

/**
 * Created by Pierre on 31/03/2015.
 */
public class UserServiceImpl implements UserService{

    private final Logger logger = Logger.getLogger(UserServiceImpl.class);

    /**
     *  State : OK (01/04/2015)
     * @param user user to save in database
     */
    public void createUser(User user) {

        logger.info(user.getLogin());

        Map<String,String> userProperties = new HashMap<>();
        userProperties.put("userId", String.valueOf(user.hashCode()));
        userProperties.put("userLogin", user.getLogin());
        userProperties.put("userPassword", user.getLogin());
       userProperties.put("userLastName", user.getLastName());
        userProperties.put("userFirstName", user.getFirstName());
        userProperties.put("userProjects", "Projects : " + user.getLogin());
       Redis.insertHash("User", user.getLogin(), userProperties);
    }

    /**
     * State : ??
     * @return the list of all the users of the database with their projects and tasks
     */
    public List<User> getUsers () {
        List<User> result = new ArrayList<>();

        Set users = Redis.getDatas();
        for (Object user1 : users) {

            String idUser = user1.toString();
            User user = Redis.getUserFromID(idUser);
            result.add(user);

        }

        return result;

    }
}
