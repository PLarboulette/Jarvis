package perso.jarvis.services;

import perso.jarvis.beans.User;

import java.util.List;

/**
 * Created by Pierre on 31/03/2015.
 */
public interface UserService {

    // State OK (01/04/2015)
    void createUser(User user);

    // State OK (01/04/2015)
    List<User> getUsers();


}
