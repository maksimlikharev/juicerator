package com.thomson.guice.impl;

import java.util.Date;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;
import com.thomson.guice.GenericInterface;
import com.thomson.guice.Person;

public class PersonImpl implements Person {
	private final String first; 
	private final Date   dob;
	private final Provider<String> last;

	@Inject
	PersonImpl( @Named("com.thomson.guice.name") String first, Provider<String> last, @Named("dob") GenericInterface<Date> dob ) {
		this.first = first;
		this.last  = last;
		this.dob   = dob.getValue();
	}

	public Date getDOB() {
		return dob;
	}
	
	public String getName() {
		return first + " " + last.get();
	}
}
