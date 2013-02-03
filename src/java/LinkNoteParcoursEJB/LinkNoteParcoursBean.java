/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LinkNoteParcoursEJB;

import ParcoursEJB.Parcours;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateful(name="LinkNoteParcours", mappedName="LinkNoteParcours")
public class LinkNoteParcoursBean implements LinkNoteParcoursLocal {
    @PersistenceContext(unitName="GeoNotesPU")
    private EntityManager em;
    
    @Override
    public void create(LinkNoteParcours l) {
        em.persist(l);
    }

    @Override
    public void edit(LinkNoteParcours l) {
        em.merge(l);
    }

    @Override
    public void remove(LinkNoteParcours l) {
        em.remove(em.merge(l));
    }

    @Override
    public int count() {
        Query q = em.createQuery("SELECT COUNT(l) FROM LinkNoteParcours AS l");
        Number val = (Number) q.getSingleResult();
        return(val.intValue());
    }

    @Override
    public LinkNoteParcours find(Long id) {
        return em.find(LinkNoteParcours.class, id);
    }

    @Override
    public List<LinkNoteParcours> findAll() {
        Query q = em.createQuery("SELECT l FROM LinkNoteParcours AS l");
        return(q.getResultList());
    }    

    @Override
    public List<LinkNoteParcours> findLinkForParcours(Parcours p) {
        Query q = em.createQuery("SELECT l FROM LinkNoteParcours AS l WHERE l.parcours = :parcours");
        q.setParameter("parcours", p);
        return(q.getResultList());
    }

    @Override
    public int countNoteByParcours(Parcours p) {
        Query q = em.createQuery("SELECT COUNT(l) FROM LinkNoteParcours AS l WHERE l.parcours = :parcours");
        q.setParameter("parcours", p);
        Number val = (Number) q.getSingleResult();
        return(val.intValue());
    }
    
    
}
