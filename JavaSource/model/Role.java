package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({@NamedQuery(name = Role.ALL, query = "SELECT r FROM Role r")})

public class Role extends BaseEntity implements Serializable {

	public final static String ALL = "Role.populateRoles";
	static final long serialVersionUID = 1L;
	private String roledesc;
	private String rolename;

	public Role() {
	}

	public Role(Integer roleid, String rolename) {
		this.rolename = rolename;
	}

	@ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "roles")
	private List<UserAdmin> users;

	public String getRoledesc() {
		return this.roledesc;
	}

	public void setRoledesc(String roledesc) {
		this.roledesc = roledesc;
	}

	public String getRolename() {
		return this.rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public List<UserAdmin> getUsers() {
		return users;
	}

	public void setUsers(List<UserAdmin> users) {
		this.users = users;
	}


}