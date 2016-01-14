package util;  
  
import java.util.ArrayList;  
import java.util.List;  

import javax.faces.application.FacesMessage;  
  
import javax.faces.component.UIComponent;  
import javax.faces.context.FacesContext;  
import javax.faces.convert.Converter;  
import javax.faces.convert.ConverterException;  
import javax.faces.convert.FacesConverter;

import model.Ville;
  
@FacesConverter(value="villeConverter", forClass=Ville.class) 
public class VilleConverter implements Converter {

	public static List<Ville> villes2;
	static {  
			villes2 = new ArrayList<Ville>();
	        Ville v1  = new Ville();
	        v1.setId(1);
	        v1.setVille("Casablanca");
	        villes2.add(v1);
	        Ville v2  = new Ville();
	        v2.setId(2);
	        v2.setVille("Tetouan");
	        villes2.add(v2);
	}
	
	
	
	@Override
	 public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {  
	        if (submittedValue.trim().equals("")) {  
	            return null;  
	        } else {  
	            try {  
	                int number = Integer.parseInt(submittedValue);  
	  
	                for (Ville p : villes2) {  
	                    if (p.getId() == number) {  
	                        return p;  
	                    }  
	                }  
	                  
	            } catch(NumberFormatException exception) {  
	                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid player"));  
	            }  
	        }  
	  
	        return null;  
	    }  
	@Override
	    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {  
	        if (value == null || value.equals("")) {  
	            return "";  
	        } else {  
	            return String.valueOf(((Ville) value).getId());  
	        }  
	    }    
  
  
    
  
    
}  