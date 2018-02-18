package com.marketplace.jaxrs;

import com.marketplace.controller.MktRestController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(MktRestController.class);
        register(MktExceptionMapper.class);

    }

}
