package com.thomson.guice;

import java.util.Date;

public class DOBExtractor implements GenericInterface<Date> {

	public Date getValue() {
		return new Date();
	}

}
