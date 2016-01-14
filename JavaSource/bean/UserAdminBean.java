package bean;



import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.Role;
import model.UserAdmin;
import dao.UserAdminDAO;



@SessionScoped
@ManagedBean(name = "uab")
public class UserAdminBean implements Serializable  {

	public static final String INJECTION_NAME   = "#{uab}";
	private static final long serialVersionUID = 1L;
    @EJB( name = "UserAdminDAO" )
    private UserAdminDAO das;
    
    private UserAdmin user;    
    	
    private List<Role> roleList; 

    /**
     * Default constructor
     */
    public UserAdminBean() {

    }

    
    public List<Role> getRoleList() {
            return roleList;
    }

   
    public void setRoleList(List<Role> roleList) {
            this.roleList = roleList;
    }
    public UserAdmin isValidLogin( String username, String password ) {

    	
        UserAdmin user = das.findUserByEmail( username );

        if ( user == null || !user.getPassword().equals( password ) ) {
            return null;
        }

        return user;
    }


	public UserAdmin getUser() {
		return user;
	}


	public void setUser(UserAdmin user) {
		this.user = user;
	}


	public UserAdminDAO getDas() {
		return das;
	}


	public void setDas(UserAdminDAO das) {
		this.das = das;
	}

}
                    