package pe.org.cnl.gestiondoc.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the sec_authorities database table.
 * 
 */
@Embeddable
public class AuthorityPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String username;

	private String authorithy;

	public AuthorityPK() {
	}
	public String getUsername() {
		return this.username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAuthorithy() {
		return this.authorithy;
	}
	public void setAuthorithy(String authorithy) {
		this.authorithy = authorithy;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AuthorityPK)) {
			return false;
		}
		AuthorityPK castOther = (AuthorityPK)other;
		return 
			this.username.equals(castOther.username)
			&& this.authorithy.equals(castOther.authorithy);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.username.hashCode();
		hash = hash * prime + this.authorithy.hashCode();
		
		return hash;
	}
}