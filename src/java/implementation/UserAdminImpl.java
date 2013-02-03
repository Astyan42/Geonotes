/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*package implementation;

import NoteEJB.NoteEntity;
import ParcoursEJB.ParcoursEntity;
import UserEJB.UserEntity;
import interfaces.UserAdminLocal;
import interfaces.UserAdminRemote;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateful(name="UserAdminImpl", mappedName="UserAdminImpl")
public class UserAdminImpl implements UserAdminRemote, UserAdminLocal {

    @PersistenceContext(unitName="GeoNotesPU")
    private EntityManager em;
    private UserEntity user;
    
    public UserAdminImpl(){
        user = null;
    }
    
    @Override
    public boolean login(String usr, String mdp) {
        Query q = em.createQuery("SELECT OBJECT(u) FROM Users AS u WHERE u.login = :login ");
        q.setParameter("login", usr);
        List<UserEntity> l = q.getResultList();
        if (l.size() == 1) {
            UserEntity u = l.get(0);
            if (u.getMdp().equals(mdp)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean register(String usr, String mdp) {
        Query q = em.createQuery("SELECT OBJECT(u) FROM Users AS u WHERE u.login = :login ");
        q.setParameter("login", usr);
        List<UserEntity> l = q.getResultList();
        if (l.isEmpty() && user == null ) {
            UserEntity u = new UserEntity();
            u.setLogin(usr);
            u.setMdp(mdp);
            em.persist(u);
            return true;
        }
        return false;
    }

    @Override
    public void setNote(NoteEntity n) {
        if(user != null){
            em.persist(n);
        }
    }

    @Override
    public void setParcours(ParcoursEntity p) {
        if(user != null){
            em.persist(p);
        }
    }

    @Override
    public ParcoursEntity getParcours(Long idParcours) {
        return em.find(ParcoursEntity.class, idParcours);
    }

    @Override
    public NoteEntity getNote(Long idNote) {
        return em.find(NoteEntity.class, idNote);
    }

    @Override
    public List<ParcoursEntity> findAllParcours() {
        Query q = em.createQuery("SELECT OBJECT(p) FROM Parcours");
        return(q.getResultList());
    }

    @Override
    public List<ParcoursEntity> findMyParcours() {
        Query q = em.createQuery("SELECT OBJECT(p) FROM Parcours AS p WHERE p.owner = :usr");
        q.setParameter(":usr", user);
        return(q.getResultList());
    }

    @Override
    public List<NoteEntity> findAllNotes() {
        Query q = em.createQuery("SELECT OBJECT(n) FROM Notes");
        return(q.getResultList());
    }

    @Override
    public List<NoteEntity> findMyNotes() {
        Query q = em.createQuery("SELECT OBJECT(n) FROM Notes AS n WHERE n.owner = :usr");
        q.setParameter(":usr", user);
        return(q.getResultList());
    }

    @Override
    public ParcoursEntity addNote(ParcoursEntity p, NoteEntity n, int index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ParcoursEntity removeNoteAt(ParcoursEntity p, int index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteParcours(ParcoursEntity p) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteNote(NoteEntity n) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}*/
