package com.thomson.governator;

import java.util.Date;

import com.netflix.governator.annotations.AutoBindSingleton;

public class DOBExtractor implements GenericInterface<Date> {
	public Date getValue() {
		return new Date();
	}
}
