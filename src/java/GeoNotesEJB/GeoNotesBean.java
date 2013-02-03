/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GeoNotesEJB;

import LinkNoteParcoursEJB.LinkNoteParcours;
import LinkNoteParcoursEJB.LinkNoteParcoursLocal;
import NoteEJB.Note;
import NoteEJB.NoteLocal;
import ParcoursEJB.Parcours;
import ParcoursEJB.ParcoursLocal;
import UserEJB.User;
import UserEJB.UserLocal;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import sun.misc.BASE64Encoder;

/**
 *
 * @author Michael
 */
@Stateful(name = "GeoNote", mappedName = "GeoNote")
public class GeoNotesBean implements GeoNotesRemote {

    @EJB
    NoteLocal nb;
    @EJB
    ParcoursLocal pb;
    @EJB
    UserLocal ub;
    @EJB
    LinkNoteParcoursLocal lb;
    User usr;

    private String encrypt(String plaintext) {
        MessageDigest md = null;
     try { 
         md = MessageDigest.getInstance("SHA");
      //step 2 
     } catch(NoSuchAlgorithmException e) { 
        
     }  try { 
         md.update(plaintext.getBytes("UTF-8"));
      //step 3 
    } catch(UnsupportedEncodingException e) { 
    } byte raw[] = md.digest(); //step 4 
    String hash = (new BASE64Encoder()).encode(raw); //step 5 
    return hash; //step 6 
    }
    
    @Override
    public boolean isLogged() {
        return (usr != null);
    }

    @Override
    public void logout() {
        usr = null;
    }

    @Override
    public boolean login(String user, String mdp) {
        if (usr == null && ub.findByLogin(user) != null) {
            User u = ub.findByLogin(user);
            if (u.getMdp().equals(encrypt(mdp))) {
                usr = u;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean register(String user, String mdp) {
        if (usr == null && !user.equals("") && mdp.length() >= 4 && ub.findByLogin(user) == null) {
            User u = new User();
            u.setLogin(user);            
            u.setMdp(encrypt(mdp));
            ub.create(u);
            return true;
        }
        return false;
    }

    @Override
    public Long setNote(String desc, double lat, double lon, double alt) {
        if (usr != null && !desc.equals("")) {
            Note n = new Note();
            n.setDescription(desc);
            n.setLatitude(lat);
            n.setLongitude(lon);
            n.setAltitude(alt);
            n.setOwner(usr);
            nb.create(n);
            return (n.getId());
        }
        return -1L;
    }

    @Override
    public void setParcours(Note[] noteTab, String title) {
        if (usr != null) {
            Parcours p = new Parcours();
            p.setOwner(usr);
            p.setTitre(title);
            ArrayList<LinkNoteParcours> collec = new ArrayList<LinkNoteParcours>();
            int ordre = 0;
            for (Note n : noteTab) {
                LinkNoteParcours l = new LinkNoteParcours();
                l.setNote(n);
                l.setP(p);
                l.setOrdre(ordre);
                ordre++;
                collec.add(l);
            }
            p.setLinks(collec);
            pb.create(p);
        }
    }

    @Override
    public Parcours getParcours(Long idParcours) {
        return pb.find(idParcours);
    }

    @Override
    public Note getNote(Long idNote) {
        return nb.find(idNote);
    }

    @Override
    public List<Parcours> findAllParcours() {
        return pb.findAll();
    }

    @Override
    public List<Parcours> findMyParcours() {
        return pb.findMine(usr);
    }

    @Override
    public List<Note> findAllNotes() {
        return nb.findAll();
    }

    @Override
    public List<Note> findMyNotes() {
        return nb.findMine(usr);
    }

    @Override
    public Parcours addNote(Parcours p, Note n, int index) {
        if (usr != null) {
            int nbNote = lb.countNoteByParcours(p);
            if (index < 0 || index >= nbNote) {
                LinkNoteParcours l = new LinkNoteParcours();
                l.setNote(n);
                l.setOrdre(nbNote);
                l.setP(p);
                lb.create(l);
                p.getLinks().add(l);
            } else {
                for (LinkNoteParcours l : p.getLinks()) {
                    if (l.getOrdre() >= index) {
                        l.setOrdre(l.getOrdre() + 1);
                    }
                }
                LinkNoteParcours l = new LinkNoteParcours();
                l.setNote(n);
                l.setOrdre(index);
                l.setP(p);
                lb.create(l);
                p.getLinks().add(l);
            }
            pb.edit(p);
            return p;
        }
        return null;
    }

    @Override
    public Parcours removeNoteAt(Parcours p, int index) {
        if (usr != null) {
            int nbNote = lb.countNoteByParcours(p);
            if (index < 0 || index >= nbNote) {
                LinkNoteParcours toRem = null;
                for (LinkNoteParcours l : p.getLinks()) {
                    if (l.getOrdre() == nbNote - 1) {
                        toRem = l;
                        break;
                    }
                }
                p.getLinks().remove(toRem);
            } else {
                LinkNoteParcours toRem = null;
                for (LinkNoteParcours l : p.getLinks()) {
                    if (l.getOrdre() == nbNote - 1) {
                        toRem = l;
                        break;
                    }
                }
                p.getLinks().remove(toRem);
                for (LinkNoteParcours l : p.getLinks()) {
                    if (l.getOrdre() >= index) {
                        l.setOrdre(l.getOrdre() - 1);
                    }
                }
            }
            pb.edit(p);
            return p;
        }
        return null;
    }

    @Override
    public void deleteParcours(Parcours p) {
        if (usr != null) {
            if (p.getOwner().equals(usr)) {
                pb.remove(p);
            }
        }
    }

    @Override
    public void deleteNote(Note n) {
        if (usr != null) {
            if (n.getOwner().equals(usr)) {
                nb.remove(n);
            }
        }
    }

    @Override
    public HashMap<Integer, Note> getNoteForParcours(Parcours p) {
        Collection<LinkNoteParcours> links = lb.findLinkForParcours(p);
        links.size();
        HashMap<Integer, Note> hmap = new HashMap<Integer, Note>();
        for (LinkNoteParcours l : links) {
            hmap.put(l.getOrdre(), l.getNote());
        }
        hmap.size();
        return hmap;
    }

    @Override
    public void updateNote(Long id, String newDesc) {
        if (usr != null) {
            Note n = nb.find(id);
            if (n.getOwner().equals(usr)) {
                n.setDescription(newDesc);
            }
        }
    }
}
