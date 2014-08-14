package es.tododev.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.logging.Logger;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Names;

import es.tododev.aop.LogMethod;
import es.tododev.aop.LogMethod.LogIt;

public class ResourceSampleTest extends JerseyTest {

	private final static String EXPECTED_STRING = "I'm a test!";
	private final static Injector injector = Guice.createInjector(new AbstractModule() {
		@Override
		public void configure() {
			bind(String.class).annotatedWith(Names.named(ResourceSample.STRING_KEY)).toInstance(EXPECTED_STRING);
			bind(ServiceSample.class);
			bindInterceptor(Matchers.any(), Matchers.annotatedWith(LogIt.class), new LogMethod());
		}
	});
	
	@Test
	public void checkGuiceInjection(){
		Response response = target(ResourceSample.PATH).request().get();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		String string = response.readEntity(String.class);
		assertEquals(EXPECTED_STRING, string);
	}
	
	@Override
	protected Application configure() {
		try {
			MockitoAnnotations.initMocks(this);
			super.enable(TestProperties.LOG_TRAFFIC);
		}catch(Exception e){
			fail("Uncontrolled error");
		}
		return new RestConfig(injector);
	}

	@Override
	protected void configureClient(ClientConfig config) {
		super.configureClient(config);
		config.register(new LoggingFilter(Logger.getLogger(LoggingFilter.class.getName()), true));
	}
	
}
