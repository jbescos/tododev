package es.tododev.rest;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

public class RestConfig extends ResourceConfig {

	@Inject
    public RestConfig() {
    	this(Guice.createInjector(new Module(){
			@Override
			public void configure(Binder arg0) {
				// TODO Auto-generated method stub
			}
    	}));
    }
    
    // Test
    public RestConfig(Injector injector) {
    	packages(ResourceSample.class.getPackage().getName());
    	register(ApplyGuiceContextFilter.class);
    	register(new LoggingFilter(Logger.getLogger(LoggingFilter.class.getName()), true));
 		
        property(ServerProperties.TRACING, "ALL");
    	register(new RestBinder(injector));
    }
    
    private static class RestBinder extends AbstractBinder{

    	private final Injector injector;
    	
    	private RestBinder(Injector injector){
    		this.injector = injector;
    	}
    	
		@Override
		protected void configure() {
			bind(injector).to(Injector.class);
		}
 		
 	}

}
