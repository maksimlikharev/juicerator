package com.thomson.guice;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;
import com.thomson.guice.impl.PersonImpl;

public class InjectionModule extends AbstractModule {

	/**
	 * Simple properties extractor, we be used for the property values injection 
	 * @return Properties map
	 */
	private Properties getProperties() {
		Properties properties = new Properties();
		
		try {
			properties.load( InjectionModule.class.getResourceAsStream( "local.properties" ) );
		} catch (IOException e) {
		}
	
		return properties;
	}
	
	@Override
	protected void configure() {
		
		//property injection bindings
		Names.bindProperties(binder(), getProperties());

		//generic bindings, instantiate immediately
		bind(new TypeLiteral<GenericInterface<Date>>(){}).annotatedWith(Names.named("dob")).to(DOBExtractor.class).asEagerSingleton();

		//simple interface to instance binding
		bind( Person.class ).to( PersonImpl.class );
	}
	
	/**
	 * This is simple provider, if too complex can be a separate class
	 * @return String
	 */
	
	@Provides
	String userLastName() {
		return "Lastname";
	}

}
