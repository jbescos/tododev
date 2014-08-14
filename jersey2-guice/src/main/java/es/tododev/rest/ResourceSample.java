package es.tododev.rest;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path(ResourceSample.PATH)
public class ResourceSample {

	private final String name;
	public final static String STRING_KEY = "guice";
	final static String PATH = "test";
	
	@Inject
	public ResourceSample(@Named(STRING_KEY) String name){
		this.name = name;
	}
	
	@GET
	public Response getResponse(){
		return Response.ok(name).build();
	}
	
}
