package com.cairone.im.ui.ctrl;

import java.io.InputStream;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@Path("/convert")
public interface ConvertService {

	@GET
	@Path("echo")
	public String echo(@QueryParam("msg") String message);
	
	@POST
	@Path("/to-png")
	public InputStream convert(
			@PathParam("input") String input, 
			@PathParam("output") String output,
			InputStream is);
}
