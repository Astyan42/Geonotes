/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GeoNotesEJB;

import NoteEJB.Note;
import ParcoursEJB.Parcours;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Jacky
 */
@Remote
public interface GeoNotesRemote {
    /**
     * 
     * @return true si un user est défini dans le bean false sinon
     */
    boolean isLogged();
    
    /**
     * déconnecte l'utilisateur
     */
    void logout();
    
    /**
     * retourne l'id de l'utilisateur, -1 sinon
     * @param usr
     * @param mdp
     * @return l'id de l'utilisateur, -1 sinon
     */
    boolean login(String usr, String mdp);

    /**
     * ajoute un usr dans la base de donnée ssi aucun user n'est identifié
     * @param usr
     * @param mdp
     * @return true si l'enregistrement s'est bien passé, false sinon
     */
    boolean register(String usr, String mdp);
    
    /**
     * crée une note dans la base de donnée
     * @param description description de la note
     * @param latitude latitude de la note
     * @param longitude longitude de la note
     * @param altitude altitude de la note
     */
    Long setNote(String description, double latitude, double longitude, double altitude );
    
    /**
     * crée un parcours dans la base de donnée
     * @param p
     */
    void setParcours(Note[] noteTable, String title);
    
    /**
     *
     * @param idParcours l'id du parcours qu'on voudra récupérer
     * @return le parcours correspondant
     */
    Parcours getParcours(Long idParcours);
    
    /**
     *
     * @param idParcours l'id de la note qu'on voudra récupérer
     * @return la note correspondant
     */
    Note getNote(Long idNote);
    
    /**
     * 
     * @return L'ensemble des parcours en base
     */
    List<Parcours> findAllParcours();
    
    /**
     * 
     * @return L'ensemble des parcours que j'ai créé en base
     */
    List<Parcours> findMyParcours();
    
    /**
     * 
     * @return L'ensemble des parcours
     */
    List<Note> findAllNotes();
    
    /**
     * 
     * @return L'ensemble de mes parcours
     */
    List<Note> findMyNotes();
    
    /** 
     * Ajoute une note à un parcours, si index vaut -1, la note sera ajoutée à la fin
     * @param p parcours à modifier
     * @param n Note à ajouter
     * @param index place de la note dans le parcours
     * @return le nouveau parcours avec la note insérée
     */
    Parcours addNote(Parcours p, Note n, int index);
    
    /**
     * Supprime une note du parcours, si l'index est invalide, retourne le parcours initial
     * @param p parcours à modifier
     * @param n Note à ajouter
     * @param index place de la note dans le parcours
     * @return le nouveau parcours avec la note isupprimée
     */
    Parcours removeNoteAt(Parcours p,int index);
    
        /**
     * Supprime le parcours de la base;
     * @param p
     */
    void deleteParcours(Parcours p);
    
    /**
     * Supprime la note de la base ( si la note existe dans un ou plusieurs parcours, elle ne peut pas être supprimée )
     * @param n 
     */
    void deleteNote(Note n);
    
    /**
     *
     * @param p
     * @return la collection de notes composant le parcours
     */
    HashMap<Integer, Note> getNoteForParcours(Parcours p);
    
    /**
     * update the note with id id
     * @param id
     * @param newDesc
     */
    void updateNote(Long id, String newDesc);
}
