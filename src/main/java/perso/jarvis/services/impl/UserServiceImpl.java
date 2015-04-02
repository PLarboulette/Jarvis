package perso.jarvis.services.impl;

import com.sun.corba.se.impl.util.RepositoryId;
import perso.jarvis.beans.User;
import perso.jarvis.redis.Redis;
import perso.jarvis.services.UserService;

import javax.swing.text.html.HTMLDocument;
import java.util.*;

/**
 * Created by Pierre on 31/03/2015.
 */
public class UserServiceImpl implements UserService{

    /**
     *  State : OK (01/04/2015)
     * @param user user to save in database
     */
    public void createUser(User user) {

        Map<String,String> userProperties = new HashMap<String,String>();
        userProperties.put("userId", String.valueOf(user.hashCode()));
        userProperties.put("userLogin", user.getLogin());
        userProperties.put("userPassword", user.getPassword());
        userProperties.put("userLastName", user.getLastName());
        userProperties.put("userFirstName", user.getFirstName());
        userProperties.put("userProjects", "Projects : "+String.valueOf(user.hashCode()));
        Redis.insertHash("User",user.getLogin(),userProperties);
    }

    /**
     * State : ??
     * @return the list of all the users of the database with their projects and tasks
     */
    public List<User> getUsers () {
        List<User> result = new ArrayList<User>();

        Set users = Redis.getDatas("User");
        Iterator i = users.iterator();
        while (i.hasNext()) {

            String idUser = i.next().toString();
            User user = Redis.getUserFromID(idUser);
            result .add(user);

        }

        return result;

    }
}
