package bean;
import java.io.IOException;
import java.io.Serializable;





import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import model.UserAdmin;

@RequestScoped
@ManagedBean(name="loginBean")
public class LoginBean extends AbstractBean implements Serializable  {
	   private static final long serialVersionUID = 1L;

	    @ManagedProperty( value = UserAdminBean.INJECTION_NAME )
	    private UserAdminBean          userAdminBean;

	    private String            username;
	    private String            password;
	    private String            msg;
	    
	    
	    public void login2( AjaxBehaviorEvent event ) {
	        UserAdmin user = userAdminBean.isValidLogin( username, password );
	    	
	       
	        if ( user != null ) {
	        	userAdminBean.setUser( user );
	        	
	            FacesContext context = FacesContext.getCurrentInstance();
	            HttpServletRequest request = (HttpServletRequest) context
	                    .getExternalContext().getRequest();
	            request.getSession().setAttribute( "user", user );
	            
	            ExternalContext context1 = FacesContext.getCurrentInstance()
	                    .getExternalContext();
	            try {
	            	
	            	if(user.getRoles().get(0).getRolename().equals("administrateur"))
	            	{
	            		context1.redirect( context1.getRequestContextPath()
		                        + "/admincp/administrateur/dashboard.xhtml" );
	            	}
	            	else if(user.getRoles().get(0).getRolename().equals("moderateur"))
	            	{
	            		context1.redirect( context1.getRequestContextPath()
		                        + "/admincp/moderateur/dashboard.xhtml" );
	            	}
	                
	                
	                
	            } catch ( IOException e ) {
	                e.printStackTrace();
	            }
	            
	            

	        } else if ( username.isEmpty() || password.isEmpty() ) {
	            msg = "Il faut remplir tous les champs.";

	        } else {
	            msg = "Username ou mot de passe incorrecte(s).";

	        }

	    }
	    
	    public String logout() {
	    	getRequest().getSession().invalidate();
	        return "/login.xhtml?faces-redirect=true";	
	    }
	    
	    private HttpServletRequest getRequest() {
	        return (HttpServletRequest) FacesContext.getCurrentInstance()
	                .getExternalContext().getRequest();
	    }

		public String getMsg() {
			return msg;
		}


		public void setMsg(String msg) {
			this.msg = msg;
		}


		public UserAdminBean getUserAdminBean() {
			return userAdminBean;
		}


		public void setUserAdminBean(UserAdminBean userAdminBean) {
			this.userAdminBean = userAdminBean;
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
}
