/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package NoteEJB;

import UserEJB.User;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Michael
 */
@Stateful(name="Note", mappedName="Note")
public class NoteBean implements NoteLocal {
    @PersistenceContext(unitName="GeoNotesPU")
    private EntityManager em;
    
    @Override
    public void create(Note n) {
        em.persist(n);
    }

    @Override
    public void edit(Note n) {
        em.merge(n);
    }

    @Override
    public void remove(Note n) {
        em.remove(em.merge(n));
    }

    @Override
    public int count() {
        Query q = em.createQuery("SELECT COUNT(n) FROM Note");
        Number val = (Number) q.getSingleResult();
        return(val.intValue());
    }

    @Override
    public Note find(Long id) {
        return em.find(Note.class, id);
    }

    @Override
    public List<Note> findAll() {
        Query q = em.createQuery("SELECT n FROM Note AS n");
        return(q.getResultList());
    }

    @Override
    public List<Note> findMine(User u) {
        Query q = em.createQuery("SELECT n FROM Note AS n WHERE n.owner = :usr");
        q.setParameter("usr", u);
        return(q.getResultList());
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

}
