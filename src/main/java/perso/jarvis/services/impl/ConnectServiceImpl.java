package perso.jarvis.services.impl;

import perso.jarvis.beans.User;
import perso.jarvis.redis.Redis;
import perso.jarvis.services.ConnectService;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Pierre on 31/03/2015.
 */
public class ConnectServiceImpl implements ConnectService {

    private HashMap<String,User> tokens = new HashMap<String,User>();


    @Override
    public boolean checkLogin(String login, String password) {
        boolean authorized = false;
        Set users = Redis.getDatas("User");
        Iterator i = users.iterator();
        while (i.hasNext()) {
            String idUser = i.next().toString();
            User user = Redis.getUserFromID(idUser);
            if (user.getLogin().equals(login)) {
                if (user.getPassword().equals(password)) {
                    authorized = true;
                    break;
                }
            }
        }
        return authorized;
    }

    @Override
    public void addToken(String token, User user) {

        tokens.put(token,user);
    }
}
