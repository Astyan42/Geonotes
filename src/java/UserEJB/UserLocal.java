/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UserEJB;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Michael
 */
@Local
public interface UserLocal {
    void create (User u);
    void edit   (User u);
    void remove (User u);
    int count();
    User find(Long id);
    List<User> findAll();
    User findByLogin(String login);
}
