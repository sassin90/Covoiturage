package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({@NamedQuery(name = "UserAdmin.populateUsers", query = "SELECT u FROM UserAdmin u"),
@NamedQuery(name = "User.countUsersAdminTotal", query = "SELECT COUNT(u) FROM UserAdmin u"),
@NamedQuery( name = "userAdminEmail", query = "SELECT u FROM UserAdmin u where u.email=:email" )
})
public class UserAdmin extends BaseEntity implements Serializable {
        
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
     
	
	@ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = {
        @JoinColumn(name = "User_userid")}, inverseJoinColumns = {
        @JoinColumn(name = "Role_roleid")})
    private List<Role> roles;
	
    @Column(length = 50)
    private String firstname;
     
    @Column(length = 50)
    private String lastname;
     
    @Column(length = 50)
    private String email;
     
    @Column(length = 64)
    private String password;
     
  
 
    public UserAdmin() {
       
    }
 
   
 
 
    public String getFirstname() {
        return this.firstname;
    }
 
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
 
    public String getLastname() {
        return this.lastname;
    }
 
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
 
    public String getEmail() {
        return this.email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getPassword() {
        return this.password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
 
}
