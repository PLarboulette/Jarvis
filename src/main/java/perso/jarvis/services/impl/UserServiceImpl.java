package perso.jarvis.services.impl;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.apache.log4j.Logger;
import org.bson.BSONObject;
import org.joda.time.DateTime;
import perso.jarvis.beans.User;
import perso.jarvis.mongo.Mongo;
import perso.jarvis.redis.Redis;
import perso.jarvis.services.UserService;

import java.util.*;

/**
 * Created by Pierre on 31/03/2015.
 */
public class UserServiceImpl implements UserService{

    private final Logger logger = Logger.getLogger(UserServiceImpl.class);

    /**
     *  State : OK (25/04/2015)
     * @param user user to save in database
     */
    public void createUser(User user) {
        DBObject userFOrDB = new BasicDBObject();
        userFOrDB.put("login",user.getLogin());
        userFOrDB.put("password",user.getLogin());
        userFOrDB.put("firstName",user.getFirstName());
        userFOrDB.put("lastName",user.getLastName());
        userFOrDB.put("creationDate", new DateTime().toDate());
        BasicDBList projects = new BasicDBList();
        userFOrDB.put("projects",projects);

        Mongo.insert(userFOrDB,"users");
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
