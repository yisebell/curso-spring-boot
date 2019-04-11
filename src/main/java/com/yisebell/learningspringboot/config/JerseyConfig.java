package com.yisebell.learningspringboot.config;

import javax.ws.rs.Path;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.yisebell.learningspringboot.resource.UserResourceResteasy;

@Configuration
@Path("/")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        
        register(UserResourceResteasy.class);
    }
}
