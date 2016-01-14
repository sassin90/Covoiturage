package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table
@NamedQueries( {
    @NamedQuery( name = "Ville.populateVilles", query = "SELECT u FROM Ville u" )
   })
public class Ville extends BaseEntity implements Serializable {

	 @Column( length = 50 )
	private String ville;
	 
	 @ManyToMany(mappedBy="villes_passage")
	 private List<Trajet> trajets;

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public List<Trajet> getTrajets() {
		return trajets;
	}

	public void setTrajets(List<Trajet> trajets) {
		this.trajets = trajets;
	}
	
	
}
