package br.edu.ufcg.cccpharma.user;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * An Authority object models an user authority on the CCCPharma system. An
 * authority is unique identified by an autogenerated ID number. However, the
 * "name" attribute, specified in an enumeration, makes it easily recognizable.
 * The Authority objects will be used to define the features whose access will
 * be granted to each user. The authorities are described in one of our database
 * tables.
 * 
 * @author Douglas Pereira de Lima
 * @author Fanny Batista Vieira
 * @author Mateus de Lima Oliveira
 * @author Matheus Alves dos Santos
 * 
 * @since 2018-11-12
 * @version 1.0
 * 
 */
@Entity
@Table(name = "tb_authority")
public class Authority implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Enumerated(EnumType.STRING)
	private UserRoleName name;

	/**
	 * Creates an Authority object with nulled attributes.
	 */
	public Authority() {}

	/**
	 * Creates an Authority object based on its ID number and user role name.
	 * 
	 * @param id   The unique identifier of the Authority.
	 * @param name The name of the user role in the system.
	 * 
	 */
	public Authority(long id, UserRoleName name) {
		this.id = id;
		this.name = name;
	}

	public UserRoleName getName() {
		return this.name;
	}

	public void setName(UserRoleName name) {
		this.name = name;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Generates a hash code for the Authority object based on its ID number.
	 * 
	 * @return The generated hash code for the Authority object.
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/**
	 * Verifies equality between the Authority object and the Object given as
	 * parameter. This equality will only exists if the given Object is also an
	 * Authority object and possesses the same ID number.
	 * 
	 * @param obj An object to be compare to the Authority.
	 * 
	 * @return A boolean that represents the comparison result.
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Authority other = (Authority) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name != other.name)
			return false;
		return true;
	}

	/**
	 * Generates a textual representation of the Authority object based on its ID
	 * number and user role name.
	 * 
	 * @return The textual representation of the Authority.
	 * 
	 */
	@Override
	public String toString() {
		return "ID: " + this.getId() + " - Authority Name: " + this.getAuthority();
	}

	/**
	 * Returns the name of the UserRole value in the Authority "name" attribute.
	 * 
	 * @return The name of the user role in the system.
	 * 
	 */
	@Override
	@JsonIgnore
	public String getAuthority() {
		return this.name.name();
	}

}