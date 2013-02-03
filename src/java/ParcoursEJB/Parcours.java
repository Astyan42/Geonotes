/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ParcoursEJB;

import LinkNoteParcoursEJB.LinkNoteParcours;
import UserEJB.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Jacky
 */
@Entity
@Table(name="PARCOURS")
public class Parcours implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private User owner;
    @OneToMany(cascade=CascadeType.ALL, mappedBy="parcours")    
    private Collection<LinkNoteParcours> links;
    
    private String titre;
    
    public Parcours(){
        super();
        links = new ArrayList<LinkNoteParcours>();
    }

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
        if (!(object instanceof Parcours)) {
            return false;
        }
        Parcours other = (Parcours) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Parcours[ id=" + id + " ]";
    }

    /**
     * @return the owner
     */
    public User getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(User owner) {
        this.owner = owner;
    } 

    public Collection<LinkNoteParcours> getLinks() {
        return links;
    }
    
    public void setLinks(Collection<LinkNoteParcours> links) {
        this.links = links;
    }

    /**
     * @return the titre
     */
    public String getTitre() {
        return titre;
    }

    /**
     * @param titre the titre to set
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }
    

}
