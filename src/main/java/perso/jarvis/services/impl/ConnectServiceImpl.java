package perso.jarvis.services.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import perso.jarvis.beans.User;
import perso.jarvis.mongo.Mongo;
import perso.jarvis.redis.Redis;
import perso.jarvis.services.ConnectService;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Pierre on 31/03/2015.
 */
public class ConnectServiceImpl implements ConnectService {

    private final HashMap<String,User> tokens = new HashMap<>();


    @Override
    public boolean checkLogin(String login, String password) {
        boolean authorized = false;



        List<DBObject> listUsers = Mongo.find("users",new BasicDBObject(), new BasicDBObject());

        for (DBObject user : listUsers) {
            if (user.get("login").equals(login) && user.get("password").equals(password)) {
                authorized = true;
                break;
            }
        }
        return authorized;
    }

    @Override
    public void addToken(String token, User user) {

        tokens.put(token,user);
    }
}
