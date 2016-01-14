package bean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseId;
import javax.annotation.PostConstruct;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;

import dao.UserDAO;
import model.User;

@SessionScoped
@ManagedBean( name = "profilBean" )
public class UserProfilBean implements Serializable {

    private static final long  serialVersionUID = 1L;
    private List<User>         users;

    @EJB( name = "UserDAO" )
    private UserDAO            userDAO;
    private User selectedUser;
    private List<User> filteredUser;
    private List<User> selectedUsers;
    private User newUser = new User();
 

	public User getNewUser() {
		return newUser;
	}

	public void setNewUser(User newUser) {
		this.newUser = newUser;
	}

	public UserProfilBean() {

    }
    
    @SuppressWarnings("unchecked")
	@PostConstruct
    public void init() {
        userDAO.beginTransaction();
        users = userDAO.findWithNamedQuery( "User.populateUsers" );
        
    }
    
  
    // sup 
    @SuppressWarnings("unchecked")
	public void doDeleteUsers(ActionEvent actionEvent) {
    	

        userDAO.deleteItems( selectedUsers );
        users = userDAO.findWithNamedQuery( "User.populateUsers" );
        
        

    }
    
    
    
    
    
    
    @SuppressWarnings("unchecked")
	public void createUser()
    {
    	RequestContext context = RequestContext.getCurrentInstance();  
        FacesMessage msg = null;  
        boolean userCreated = false; 
       // System.out.println("test : "+newUser.getUsername());
        User user = userDAO.findUserByUsername(newUser.getEmail());
      
        
        if(user == null) {  
        	userCreated = true;  
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Utilisateur créé avec succès", null); 
            userDAO.create(newUser);
            users = userDAO.findWithNamedQuery("User.populateUsers");
            
            
        } else {  
        	userCreated = false;  
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Username déjà existe", null);  
        }  
          
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        context.addCallbackParam("userCreated", userCreated);  
            
        
    }
      
    
    public void onEdit(RowEditEvent event) { 
    	FacesMessage msg = null;
    	User aux = (User) event.getObject();
    	User user = userDAO.findUserByEU( aux.getEmail(), aux.getId() );

    	if ( user == null ) {
    		userDAO.update(aux);
    		msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Utilisateur modifié avec succès", null);
            FacesContext.getCurrentInstance().addMessage(null, msg); 
    	 }
    	else
    	{
    		 msg = new FacesMessage( FacesMessage.SEVERITY_ERROR, "Username existe déjà.",
                     null );
             FacesContext.getCurrentInstance().addMessage( null, msg );
            
    	}
    	 
    }  
      
    public void onCancel(RowEditEvent event) {  
      
    }  
    
    
    
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	   public User getSelectedUser() {
			return selectedUser;
		}

		public void setSelectedUser(User selectedUser) {
			this.selectedUser = selectedUser;
		}

		public List<User> getFilteredUser() {
			return filteredUser;
		}

		public List<User> getSelectedUsers() {
			return selectedUsers;
		}

		public void setSelectedUsers(List<User> selectedUsers) {
			this.selectedUsers = selectedUsers;
		}

		public void setFilteredUser(List<User> filteredUser) {
			this.filteredUser = filteredUser;
		}

		public void handleFileUpload(FileUploadEvent event) {
			System.out.println("fdsfdfs");
			  if ( !PhaseId.INVOKE_APPLICATION.equals( event.getPhaseId() ) ) {
		            event.setPhaseId( PhaseId.INVOKE_APPLICATION );
		            event.queue();
		        } else {

		            try {

		                copyFile( event.getFile().getFileName(), event.getFile().getInputstream() );

		            } catch ( IOException e ) {

		                e.printStackTrace();

		            }

		        }
			
			
	        
	    }  
		 
		 
		 public void copyFile( String fileName, InputStream in ) {
		        try {
		        	

		            List<User> users = userDAO.findWithNamedQuery( "User.populateUsers" );
		            

		            for ( User u : users )
		            {
		                if ( u.getPhoto().equals( fileName ) )
		                {
		                    FacesMessage msg = new FacesMessage( "Un fichier avec le nom : " + fileName + " existe déjà!", null );
		                    msg.setDetail( "" );

		                    msg.setSeverity( FacesMessage.SEVERITY_ERROR );
		                    FacesContext.getCurrentInstance().addMessage( null, msg );
		                    return;
		                }
		            }

		            // write the inputStream to a FileOutputStream
		            OutputStream out = new FileOutputStream( new File( "C:\\"+fileName ) );

		            int read = 0;
		            byte[] bytes = new byte[1024];

		            while ( ( read = in.read( bytes ) ) != -1 ) {
		                out.write( bytes, 0, read );
		            }

		            in.close();
		            out.flush();
		            out.close();

		            FacesMessage msg = new FacesMessage( "Le fichier : " + fileName + " est tansmis avec succès!" );
		            msg.setDetail( "" );
		            msg.setSeverity( FacesMessage.SEVERITY_INFO );
		            FacesContext.getCurrentInstance().addMessage( null, msg );



		        } catch ( IOException e ) {
		            System.out.println( e.getMessage() );
		        }
		    } 
		 
		 
		 
		 

    

}