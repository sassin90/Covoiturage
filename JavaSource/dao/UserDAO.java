package dao;

import java.util.HashMap;
import java.util.Map;

import model.User;

import javax.ejb.Stateless;

@Stateless( name = "UserDAO" )
public class UserDAO extends GenericDAO<User> {

    public UserDAO() {
        super( User.class );
    }
    
    
    public User findUserByEmail( String Email)
    {
    	Map < String, Object> parameters = new HashMap<String, Object>();
    	
    	parameters.put("email",Email);
    	
		return super.findOneResult("userEmail", parameters);
    	
    }
    
    public User findUserByUsername( String username)
    {
    	Map < String, Object> parameters = new HashMap<String, Object>();
    	
    	parameters.put("username",username);
    	
		return super.findOneResult("findUserByUsername", parameters);
    	
    }
    
    public User findUserByEU(String username, Integer id)
    {
    	Map < String, Object> parameters = new HashMap<String, Object>();
    	
    	parameters.put("username",username);
    	parameters.put("id",id);
    	
		return super.findOneResult("findUserByEU", parameters);
    }

}
