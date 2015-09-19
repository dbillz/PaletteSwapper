package com.dan.paleteSwapper;

public enum Tolerance {
	ZERO (0),
	VERY_LOW (5000),
	LOW(10000),
	NORMAL(100000),
	HIGH(500000),
	EXTREME(1000000);
	
	private final int filterLimit;
	Tolerance(int filterLimit){
		this.filterLimit = filterLimit;
	}
	public int getFilterLimit(){
		return filterLimit;
	}
}
