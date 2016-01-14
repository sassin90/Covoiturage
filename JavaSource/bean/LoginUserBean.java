package bean;
import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import model.User;

@RequestScoped
@ManagedBean(name="loginUserBean")
public class LoginUserBean extends AbstractBean implements Serializable  {
	   private static final long serialVersionUID = 1L;

	    @ManagedProperty( value = UserBean.INJECTION_NAME )
	    private UserBean          userBean;

	    private String            username;
	    private String            password;
    
	    
	    public void login2( ActionEvent actionEvent ) {
	        User user = userBean.isValidLogin( username, password );
	   	 FacesMessage msg = null;
	       
	        if ( user != null ) {
	        	userBean.setUser( user );
	        
	            FacesContext context = FacesContext.getCurrentInstance();
	            HttpServletRequest request = (HttpServletRequest) context
	                    .getExternalContext().getRequest();
	            request.getSession().setAttribute( "userLogin", user );
	            
	            ExternalContext context1 = FacesContext.getCurrentInstance()
	                    .getExternalContext();
	          
	            	
	            	
	            		try {
							context1.redirect( context1.getRequestContextPath()
							        + "/index.xhtml" );
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	            
	            

	        } else if ( username.isEmpty() || password.isEmpty() ) {
	            
	            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Il faut remplir tous les champs.", "");
	          

	        } else {
	          
	            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username ou mot de passe incorrecte(s).", "");
	            
	        }
	        FacesContext.getCurrentInstance().addMessage(null, msg); 

	    }
	    
	    public String logout() {
	    	getRequest().getSession().invalidate();
	        return "/login.xhtml?faces-redirect=true";	
	    }
	    
	    private HttpServletRequest getRequest() {
	        return (HttpServletRequest) FacesContext.getCurrentInstance()
	                .getExternalContext().getRequest();
	    }



		public String getUsername() {
			return username;
		}


		public void setUsername(String username) {
			this.username = username;
		}


		public String getPassword() {
			return password;
		}


		public void setPassword(String password) {
			this.password = password;
		}

		public UserBean getUserBean() {
			return userBean;
		}

		public void setUserBean(UserBean userBean) {
			this.userBean = userBean;
		}
}
