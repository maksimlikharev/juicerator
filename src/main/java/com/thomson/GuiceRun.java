package com.thomson;

import java.text.SimpleDateFormat;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.thomson.guice.InjectionModule;
import com.thomson.guice.Person;

public class GuiceRun {
	public static void main( String[] args ) {
		try {
			final Injector injector = Guice.createInjector( new InjectionModule() );
			
			Person p = injector.getInstance( Person.class );
			
			System.out.println( "User name: " + p.getName() + ", dob: " + SimpleDateFormat.getDateInstance( SimpleDateFormat.SHORT ).format( p.getDOB() ));
		}
		catch( Exception e ) {
			System.out.println( e.getMessage() );
		}
		
		
	}
}
