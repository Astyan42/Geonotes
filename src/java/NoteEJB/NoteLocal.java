/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package NoteEJB;

import UserEJB.User;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Michael
 */
@Local
public interface NoteLocal {
    void create(Note n);
    void edit(Note n);
    void remove(Note n);
    int count();
    Note find(Long id);
    List<Note> findAll();    
    List<Note> findMine(User usr);
    
}
