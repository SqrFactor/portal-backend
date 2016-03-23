package com.sqrfactor.model;

public class EnrichedFeed extends Feed{
	
	private String name;
	
	public EnrichedFeed(Feed feed, String name) {
		super(feed);
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