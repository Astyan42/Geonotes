/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ParcoursEJB;

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
@Stateful(name="Parcours", mappedName="Parcours")
public class ParcoursBean implements ParcoursLocal{

    @PersistenceContext(unitName="GeoNotesPU")
    private EntityManager em;
        
    @Override
    public void create(Parcours p) {
        em.persist(p);
    }

    @Override
    public void edit(Parcours p) {
        em.merge(p);
    }

    @Override
    public void remove(Parcours p) {
        em.remove(em.merge(p));
    }

    @Override
    public int count() {
        Query q = em.createQuery("SELECT COUNT(p) FROM Parcours ");
        Number val = (Number) q.getSingleResult();
        return(val.intValue());
    }

    @Override
    public Parcours find(Long id) {
        return em.find(Parcours.class, id);
    }

    @Override
    public List<Parcours> findAll() {        
        Query q = em.createQuery("SELECT p FROM Parcours AS p");
        return(q.getResultList());
    }

    @Override
    public List<Parcours> findMine(User usr) {
        Query q = em.createQuery("SELECT p FROM Parcours AS p WHERE p.owner = :usr");
        q.setParameter("usr", usr);
        return(q.getResultList());
    }

}
