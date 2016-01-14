package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
@Entity
@Table
@NamedQueries( {
    @NamedQuery( name = "Trajet.populateTrajets", query = "SELECT u FROM Trajet u" )
   }

)
public class Trajet extends BaseEntity implements Serializable {
	
	 private static final long  serialVersionUID = 1L;
	 @Column( length = 50 ) 
	private String depart;
	 
	 @Column( length = 50 ) 
		private String prix;
	 
	 @ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "user_id", nullable = false)
	    private User user ;
	 
	 
	 
	
	 
	 
	 public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPrix() {
		return prix;
	}

	public void setPrix(String prix) {
		this.prix = prix;
	}
	@ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	    @JoinTable(name = "trajet_villes", joinColumns = {
	        @JoinColumn(name = "trajet_id")}, inverseJoinColumns = {
	        @JoinColumn(name = "ville_id")})
	 private List<Ville> villes_passage;
	 
	
	private String option_trajet;
	 
	
	public String getOption_trajet() {
		return option_trajet;
	}

	public void setOption_trajet(String option_trajet) {
		this.option_trajet = option_trajet;
	}
	@Column( length = 50 ) 
		private Date date_depart;
	private Date date_arrive;
	
	
	
	

	public Date getDate_depart() {
		return date_depart;
	}

	public void setDate_depart(Date date_depart) {
		this.date_depart = date_depart;
	}

	public Date getDate_arrive() {
		return date_arrive;
	}

	public void setDate_arrive(Date date_arrive) {
		this.date_arrive = date_arrive;
	}
	@Column( length = 50 )
	private String arrivee;
	 
	 
	
    public String getDepart() {
		return depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	public String getArrivee() {
		return arrivee;
	}

	public void setArrivee(String arrivee) {
		this.arrivee = arrivee;
	}

	public List<Ville> getVilles_passage() {
		return villes_passage;
	}

	public void setVilles_passage(List<Ville> villes_passage) {
		this.villes_passage = villes_passage;
	}

	

	

	
    
	
}
