package dao;


import java.util.HashMap;
import java.util.Map;

import model.UserAdmin;

import javax.ejb.Stateless;

@Stateless( name = "UserAdminDAO" )
public class UserAdminDAO extends GenericDAO<UserAdmin> {

    public UserAdminDAO() {
        super( UserAdmin.class );
    }
    
    public UserAdmin findUserByEmail( String Email)
    {
    	Map < String, Object> parameters = new HashMap<String, Object>();
    	
    	parameters.put("email",Email);
    	
		return super.findOneResult("userAdminEmail", parameters);
    	
    }

}
