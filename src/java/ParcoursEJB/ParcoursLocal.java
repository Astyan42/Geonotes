/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ParcoursEJB;

import UserEJB.User;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Michael
 */
@Local
public interface ParcoursLocal {
    void create (Parcours p);
    void edit   (Parcours p);
    void remove (Parcours p);
    int count();
    Parcours find(Long id);
    List<Parcours> findAll();  
    List<Parcours> findMine(User usr);
}
