package dao;

import javax.ejb.Stateless;

import model.Trajet;

@Stateless( name = "TrajetDAO" )
public class TrajetDAO extends GenericDAO<Trajet> {

	public TrajetDAO() {
		super(Trajet.class);
		// TODO Auto-generated constructor stub
	}

	

	
}
