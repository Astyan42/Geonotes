/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LinkNoteParcoursEJB;

import NoteEJB.Note;
import ParcoursEJB.Parcours;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Jacky
 */
@Entity
@Table(name = "LINKNOTEPARCOURS")
public class LinkNoteParcours implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Note note;
    @ManyToOne
    private Parcours parcours;
    private int ordre;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LinkNoteParcours)) {
            return false;
        }
        LinkNoteParcours other = (LinkNoteParcours) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.LinkNoteParcours[ id=" + id + " ]";
    }

    /**
     * @return the note
     */
    public Note getNote() {
        return note;
    }

    /**
     * @param note the note to set
     */
    public void setNote(Note note) {
        this.note = note;
    }

    /**
     * @return the p
     */
    public Parcours getP() {
        return parcours;
    }

    /**
     * @param p the p to set
     */
    public void setP(Parcours p) {
        this.parcours = p;
    }

    /**
     * @return the ordre
     */
    public int getOrdre() {
        return ordre;
    }

    /**
     * @param ordre the ordre to set
     */
    public void setOrdre(int ordre) {
        this.ordre = ordre;
    }
    
}
