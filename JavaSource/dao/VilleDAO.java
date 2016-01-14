package dao;

import javax.ejb.Stateless;

import model.Trajet;
import model.Ville;

@Stateless( name = "VilleDAO" )
public class VilleDAO extends GenericDAO<Ville> {

	public VilleDAO() {
		super(Ville.class);
		// TODO Auto-generated constructor stub
	}

	

	
}
