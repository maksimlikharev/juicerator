package com.thomson;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;
import com.netflix.governator.configuration.PropertiesConfigurationProvider;
import com.netflix.governator.guice.BootstrapBinder;
import com.netflix.governator.guice.BootstrapModule;
import com.netflix.governator.guice.LifecycleInjector;
import com.netflix.governator.lifecycle.LifecycleManager;
import com.thomson.governator.DOBExtractor;
import com.thomson.governator.GenericInterface;
import com.thomson.governator.Person;
import com.thomson.governator.impl.PersonImpl;


public class GovernatorRun {
	public static void main( String[] args ) {
		
		LifecycleManager manager = null;
		
		final Properties props = new Properties(); 
		
		try {
			
			props.load( com.thomson.governator.Person.class.getResourceAsStream("local.properties") );
			
			//bootstrap phase
			Injector injector = LifecycleInjector.builder()
				.withBootstrapModule( new BootstrapModule(){
				    public void configure(BootstrapBinder binder) {
				    	binder.bindConfigurationProvider().toInstance(new PropertiesConfigurationProvider(props));
				    }
				})
				.withModules(new AbstractModule() {
		
					@Override
					protected void configure() {
						//generic bindings, instantiate immediately
						bind(new TypeLiteral<GenericInterface<Date>>(){}).annotatedWith(Names.named("dob")).to(DOBExtractor.class).asEagerSingleton();
						
						//simple interface to instance binding
						bind( Person.class ).to( PersonImpl.class );
					}
					
					@Provides
					String userLastName() {
						return "Lastname";
					}
				})
			.build().createInjector();
			
			manager = injector.getInstance(LifecycleManager.class);
			
			manager.start();
			
			Person p = injector.getInstance( Person.class );
	
			System.out.println( "User name: " + p.getName() + ", dob: " + SimpleDateFormat.getDateInstance( SimpleDateFormat.SHORT ).format( p.getDOB() ));
		}
		catch( Exception e ) {
			System.out.println( e.getMessage() );
		}
		finally {
			if( manager != null ) {
				manager.close();
			}
		}
		
		
	}
}
