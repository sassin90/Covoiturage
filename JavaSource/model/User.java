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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.persistence.NamedQuery;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table
@NamedQueries( {
    @NamedQuery( name = "User.populateUsers", query = "SELECT u FROM User u" ),
    @NamedQuery( name = "userEmail", query = "SELECT u FROM User u where u.email=:email" ),
   
    @NamedQuery( name = "findUserByEU", query = "SELECT u FROM User u WHERE u.email=:email AND u.id!=:id" )
}

)
public class User extends BaseEntity implements Serializable {

    private static final long  serialVersionUID = 1L;
    
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ville_id", nullable = false)
    private Ville ville;

    @Column( length = 64, nullable = false )
    @NotEmpty( message = "Veuillez saisir un mot de passe." )
    @Size( min = 6, message = "Le mot de passe doit contenir au moins 6 caractères." )
    private String             password;

    @Column( length = 50, nullable = false )
    @NotEmpty( message = "Veuillez saisir une adresse mail." )
    @Pattern( regexp = "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)", message = "Merci de saisir une adresse mail valide." )
    private String             email;

    @Column( length = 50 )
    private String             firstname;
    
    
    @Column( length = 50 )
    private String             civilite;
    
    @Column( length = 50 )
    private String             photo;
    
    
   

    @Column( length = 50 )
    private String             lastname;
    
    @Column( length = 500 )
    private String             apropos;
    
    public String getApropos() {
		return apropos;
	}

	public void setApropos(String apropos) {
		this.apropos = apropos;
	}

	@Column( length = 50 )
    private String             age;
    
  
    
    @Column( length = 50 )
    private String             cin;


    @Column( length = 50 )
   
    private String             telephone;

    

    public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public 	Ville getVille() {
		return ville;
	}

	public void setVille(Ville ville) {
		this.ville = ville;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname( String firstname ) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname( String lastname ) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

   
    public String getCivilite() {
		return civilite;
	}

	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	

	public String getPassword() {
        return this.password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

}