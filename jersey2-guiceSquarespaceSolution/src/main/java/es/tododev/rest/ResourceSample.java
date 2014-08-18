package es.tododev.rest;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import es.tododev.aop.LogMethod.LogIt;

@Path(ResourceSample.PATH)
public class ResourceSample {

	private final String name;
	private final ServiceSample service;
	public final static String STRING_KEY = "guice";
	final static String PATH = "test";
	
	@Inject
	public ResourceSample(@Named(STRING_KEY) String name, ServiceSample service){
		this.name = name;
		this.service = service;
	}
	
	@GET
	@LogIt
	public Response getResponse(){
		System.out.println("Invoking "+this);
		service.doNothing();
		return Response.ok(name).build();
	}
	
}
