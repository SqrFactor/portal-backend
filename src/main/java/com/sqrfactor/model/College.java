package com.sqrfactor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Angad Gill
 *
 */
@Entity
@Table(name = "mstr_college_list")
public class College {
	
	@Id
	@Column(name = "colCode", nullable = false)
	private String colCode;
	
	@Column(name = "colName", nullable = false)
	private String colName;
	
	public College(){
		
	}
	
	public College(String colCode, String colName){
		super();
		this.colCode = colCode;
		this.colName = colName;
	}
	
	public College(College college){
		this.colCode = college.getColCode();
		this.colName = college.getColName();
	}

	/**
	 * @return the colCode
	 */
	public String getColCode() {
		return colCode;
	}

	/**
	 * @param colCode the colCode to set
	 */
	public void setColCode(String colCode) {
		this.colCode = colCode;
	}

	/**
	 * @return the colName
	 */
	public String getColName() {
		return colName;
	}

	/**
	 * @param colName the colName to set
	 */
	public void setColName(String colName) {
		this.colName = colName;
	}

}
