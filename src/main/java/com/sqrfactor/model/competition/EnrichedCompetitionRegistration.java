/**
 * 
 */
package com.sqrfactor.model.competition;

/**
 * @author Angad Gill
 *
 */
public class EnrichedCompetitionRegistration extends CompetitionRegistration{
	
	private String name;

	public EnrichedCompetitionRegistration(CompetitionRegistration competitionRegistration, String name){
		super(competitionRegistration);
		this.name = name;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
