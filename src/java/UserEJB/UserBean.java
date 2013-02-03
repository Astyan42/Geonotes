/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UserEJB;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Michael
 */
@Stateful(name="User", mappedName="User")
public class UserBean implements UserLocal {
    @PersistenceContext(unitName="GeoNotesPU")
    private EntityManager em;

    @Override
    public void create(User u) {
        em.persist(u);
    }

    @Override
    public void edit(User u) {
        em.merge(u);
    }

    @Override
    public void remove(User u) {
        em.remove(em.merge(u));
    }

    @Override
    public int count() {
        Query q = em.createQuery("SELECT COUNT(u) FROM Users");
        Number val = (Number) q.getSingleResult();
        return(val.intValue());
    }

    @Override
    public User find(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> findAll() {
        Query q = em.createQuery("SELECT u FROM User AS u ");
        return(q.getResultList());
    }

    @Override
    public User findByLogin(String login) {
        Query q = em.createQuery("SELECT OBJECT(u) FROM User AS u WHERE u.login = :login");
        q.setParameter("login", login);
        try{
            User u =(User) q.getResultList().get(0);
            System.err.println("User retourné : " + u.getLogin());
            return(u);
        }catch(NullPointerException e){
            System.err.println("nullPointer in findByLogin");
            return null;
        }catch(ArrayIndexOutOfBoundsException e){
            System.err.println("OutOfBounds in findByLogin");
            return null;
        }
    }
    


}
