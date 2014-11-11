package com.thomson.governator.impl;

import java.util.Date;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;
import com.netflix.governator.annotations.Configuration;
import com.thomson.governator.GenericInterface;
import com.thomson.governator.Person;

public class PersonImpl implements Person {
	
	@Configuration("com.thomson.governator.name")
	private String first;
	
	private final Date   dob;
	private final Provider<String> last;

	@Inject
	PersonImpl( Provider<String> last,  @Named("dob") GenericInterface<Date> dob ) {
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
