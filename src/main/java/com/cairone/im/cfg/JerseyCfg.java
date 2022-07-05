package com.cairone.im.cfg;

import java.util.Arrays;
import java.util.HashSet;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.cairone.im.ui.ctrl.ConvertCtrl;
import com.cairone.im.ui.ctrl.ConvertService;

import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import io.swagger.v3.oas.integration.SwaggerConfiguration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
@ApplicationPath("/api")
public class JerseyCfg extends ResourceConfig {

	public JerseyCfg() {
		register(ConvertCtrl.class);
	}
	
    @PostConstruct
    private void configureSwagger() {
    	
    	Info info = new Info()
                .title("Imagemack Web API")
                .description("Images operations with ImageMaick");
        
        OpenAPI openAPI = new OpenAPI();
        
        openAPI.info(info);
        openAPI.addServersItem(new Server().url("/"));
        
        SwaggerConfiguration swaggerConfiguration = new SwaggerConfiguration()
                .openAPI(openAPI)
                .resourceClasses(new HashSet<>(Arrays.asList(ConvertService.class.getName())))
                .prettyPrint(true);
        
        register(new OpenApiResource().openApiConfiguration(swaggerConfiguration));
    }
}
