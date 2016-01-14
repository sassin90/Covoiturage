package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;






import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import util.VilleConverter;
import dao.TrajetDAO;
import dao.VilleDAO;
import model.Trajet;
import model.Ville;


@ViewScoped
@ManagedBean( name = "TB" )
public class TrajetBean  implements Serializable {
	
	
	private static final long  serialVersionUID = 1L;
	
      
    private Date date3;  
    private List<String> villes;
    
    private List<Ville> villes2;
   
    @EJB( name = "VilleDAO" )
    private VilleDAO            villeDAO;
    
    @ManagedProperty( value = UserBean.INJECTION_NAME )
    private UserBean          userBean;
      
    public Date getDate3() {  
        return date3;  
    }  
  
    public void setDate3(Date date3) {  
        this.date3 = date3;  
    }  
	public List<Trajet> getTrajets() {
		return trajets;
	}

	public void setTrajets(List<Trajet> trajets) {
		this.trajets = trajets;
	}

	public TrajetDAO getTrajetDAO() {
		return trajetDAO;
	}

	public void setTrajetDAO(TrajetDAO trajetDAO) {
		this.trajetDAO = trajetDAO;
	}

	public Trajet getNewTrajet() {
		return newTrajet;
	}
	private List<Trajet>   trajets;
	 @EJB( name = "TrajetDAO" )
	    private TrajetDAO            trajetDAO;
	    //private Trajet selectedTrajet;
	    //private List<Trajet> filteredTrajet;
	    //private List<Trajet> selectedTrajets;
	    private Trajet newTrajet = new Trajet();
	    
	    
	    
	    public List<String> complete (String query) {  
	        villes = new ArrayList<String>();
	        villes.add("Salé"); villes.add("Tanger");
	        villes.add("ElJadida"); villes.add("Marrakech"); villes.add("Fés"); villes.add("Ifrane");
	        villes.add("Errachidia"); villes.add("Kenitra"); villes.add("Rabat"); villes.add("Nador");
	       
	        
	         
	        return villes;  
	    } 
	    public List<Ville> complete2 () {  
	        villes2 = villeDAO.findWithNamedQuery("Ville.populateVilles");
	        
	         
	        return villes2;  
	    }  
	    
	    public Trajet NewTrajet() {
			return newTrajet;
		}

		public void setNewTrajet(Trajet newTrajet) {
			this.newTrajet = newTrajet;
		}

		public TrajetBean() {

	    }
		
		
	     
	    
		@PostConstruct
	    public void init() {
	    	
	        villes2 = villeDAO.findWithNamedQuery("Ville.populateVilles");
	        }
	    @SuppressWarnings("unchecked")
		public void createTrajet()
	    {
	    	//RequestContext context = RequestContext.getCurrentInstance();  
	        FacesMessage msg = null;  
	        
	            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Trajet créé", null); 
	            System.out.println(userBean.getUser());
	            newTrajet.setUser(userBean.getUser());
	            trajetDAO.create(newTrajet);
	            trajets = trajetDAO.findWithNamedQuery("Trajet.populateTrajets");
	        FacesContext.getCurrentInstance().addMessage(null, msg); 
	        
	        
	      	            
	        
	    }
	    
	    public Date getToday() {
	        return new Date();
	    }

		public List<String> getVilles() {
			return villes;
		}

		public void setVilles(List<String> villes) {
			this.villes = villes;
		}

		public List<Ville> getVilles2() {
			return villes2;
		}

		public void setVilles2(List<Ville> villes2) {
			this.villes2 = villes2;
		}

		public VilleDAO getVilleDAO() {
			return villeDAO;
		}

		public void setVilleDAO(VilleDAO villeDAO) {
			this.villeDAO = villeDAO;
		}

		public UserBean getUserBean() {
			return userBean;
		}

		public void setUserBean(UserBean userBean) {
			this.userBean = userBean;
		}
	      
}
