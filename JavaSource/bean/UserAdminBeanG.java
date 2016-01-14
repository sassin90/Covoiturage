package bean;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import model.UserAdmin;
import dao.UserAdminDAO;

@ViewScoped
@ManagedBean(name = "UserAdminBeanG")
public class UserAdminBeanG {
    
	@EJB( name = "UserAdminDAO" )
    private UserAdminDAO            userAdminDAO;
	private String             ancienPassword;
    private String             nvPassword;
    
    @ManagedProperty( value = UserAdminBean.INJECTION_NAME )
    private UserAdminBean          userAdminBean;
    
    public void doUpdateUserParams( ActionEvent actionEvent ) {
        FacesMessage msg = null;

        UserAdmin user2 = userAdminDAO.findUserByEmail( userAdminBean.getUser().getEmail());

        if ( ancienPassword.isEmpty() )
        {
            msg = new FacesMessage( FacesMessage.SEVERITY_INFO, "Modification effectuée avec succés !", null );
            FacesContext.getCurrentInstance().addMessage( "form:success", msg );
            userAdminDAO.update( userAdminBean.getUser() );
        }
        else if ( ( (String) user2.getPassword() ).equals( ancienPassword ) )
        {
            if ( nvPassword.isEmpty() )
            {
                msg = new FacesMessage( FacesMessage.SEVERITY_ERROR, "Veuillez saisir un mot de passe.",
                        null );
                FacesContext.getCurrentInstance().addMessage( "form:nvpassword", msg );
                return;

            }
            else if ( nvPassword.length() < 6 )
            {
                msg = new FacesMessage( FacesMessage.SEVERITY_ERROR,
                        "Le mot de passe doit contenir au moins 6 caractères.",
                        null );
                FacesContext.getCurrentInstance().addMessage( "form:nvpassword", msg );
                return;
            }

            msg = new FacesMessage( FacesMessage.SEVERITY_INFO, "Modification effectuée avec succés !", null );
            FacesContext.getCurrentInstance().addMessage( "form:success", msg );

            userAdminBean.getUser().setPassword( nvPassword );
            userAdminDAO.update( userAdminBean.getUser() );
            nvPassword = "";
            ancienPassword = "";

        }
        else
        {
            msg = new FacesMessage( FacesMessage.SEVERITY_ERROR, "Mot de passe incorrect.",
                    null );
            FacesContext.getCurrentInstance().addMessage( "form:password", msg );
        }
    }

	public UserAdminDAO getUserAdminDAO() {
		return userAdminDAO;
	}

	public void setUserAdminDAO(UserAdminDAO userAdminDAO) {
		this.userAdminDAO = userAdminDAO;
	}

	public String getAncienPassword() {
		return ancienPassword;
	}

	public void setAncienPassword(String ancienPassword) {
		this.ancienPassword = ancienPassword;
	}

	public String getNvPassword() {
		return nvPassword;
	}

	public void setNvPassword(String nvPassword) {
		this.nvPassword = nvPassword;
	}

	public UserAdminBean getUserAdminBean() {
		return userAdminBean;
	}

	public void setUserAdminBean(UserAdminBean userAdminBean) {
		this.userAdminBean = userAdminBean;
	}
}
