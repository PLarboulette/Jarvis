package perso.jarvis.services;

import perso.jarvis.beans.User;

/**
 * Created by Pierre on 31/03/2015.
 */
public interface ConnectService {

    boolean checkLogin(String login, String password);
    void addToken(String token, User user);


}
