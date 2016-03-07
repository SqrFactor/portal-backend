package com.sqrfactor.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author Angad Gill
 *
 */
@Entity
@Table(name="profile")
public class Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	
	@Size(min= 3, max= 50)
	@Column(name = "firstName", nullable = false)
	String firstName;
	
	@Size(min= 3, max= 50)
	@Column(name = "lastName", nullable = false)
	String lastName;
	
	@Size(min= 3, max= 50)
	@Column(name = "collegeName", nullable = false)
	String collegeName;
	
	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "dateOfBirth", nullable = false)
	Date dateOfBirth;
	
	public Profile(){
		
	}
	
	public Profile(long id, String firstName, String lastName, String collegeName, Date dateOfBirth) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.collegeName = collegeName;
		this.dateOfBirth = dateOfBirth;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	 @Override
	    public int hashCode() {
	        final int prime = 31;
	        int result = 1;
	        result = (int) (prime * result + id);
	        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
	        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
	        
	        return result;
	    }
	 
	    @Override
	    public boolean equals(Object obj) {
	        if (this == obj)
	            return true;
	        if (obj == null)
	            return false;
	        if (!(obj instanceof Profile))
	            return false;
	        Profile other = (Profile) obj;
	        if (id != other.id)
	            return false;
	        if (firstName == null) {
	            if (other.firstName != null)
	                return false;
	        } else if (!firstName.equals(other.firstName))
	            return false;
	        return true;
	    }
}
