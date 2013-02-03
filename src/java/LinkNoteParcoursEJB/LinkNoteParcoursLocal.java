/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LinkNoteParcoursEJB;

import ParcoursEJB.Parcours;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Jacky
 */
@Local
public interface LinkNoteParcoursLocal {
    void create (LinkNoteParcours l);
    void edit   (LinkNoteParcours l);
    void remove (LinkNoteParcours l);
    int count();
    LinkNoteParcours find(Long id);
    List<LinkNoteParcours> findAll();
    List<LinkNoteParcours> findLinkForParcours(Parcours p);
    int countNoteByParcours(Parcours p);
}
