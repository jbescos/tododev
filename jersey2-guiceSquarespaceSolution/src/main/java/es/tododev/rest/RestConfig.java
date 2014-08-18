package es.tododev.rest;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.squarespace.jersey2.guice.JerseyServiceLocatorGeneratorSPI;

public class RestConfig extends ResourceConfig {

	@Inject
    public RestConfig() {
    	this(new AbstractModule(){

			@Override
			protected void configure() {
				// TODO Auto-generated method stub
			}

    	});
    }
    
    // Test
    public RestConfig(final Module ... modules) {
    	packages(ResourceSample.class.getPackage().getName());
    	register(new LoggingFilter(Logger.getLogger(LoggingFilter.class.getName()), true));
 		
        property(ServerProperties.TRACING, "ALL");
    	register(new JerseyServiceLocatorGeneratorSPI() {
			@Override
			protected List<? extends Module> modules() {
				return Arrays.asList(modules);
			}
		});
    }

}
