package com.cairone.im.ui.ctrl;

import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/convert")
public interface ConvertService {

	@GET
	@Path("echo")
	public String echo(@QueryParam("msg") String message);
	
	@POST
	@Path("/to-png")
	public Response convertToPng(File file);
}
