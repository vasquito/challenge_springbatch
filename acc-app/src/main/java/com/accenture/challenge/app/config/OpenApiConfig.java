package com.accenture.challenge.app.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class OpenApiConfig {

    private String version;

    @Bean
    public OpenAPI customOpenAPI(
            ServletContext servletContext,
            @Value("${info.app.name}") String apiName,
            @Value("${info.app.description}") String apiDescription,
            @Value("${info.app.version}") String apiVersion,
            @Value("${info.app.urlDoc}") String apiUrlDoc
    ) {
        version = apiVersion;

        List<Server> servers = new ArrayList<>();
        servers.add(new Server().url(servletContext.getContextPath()));
        servers.add(new Server().url("/default"));

        return new OpenAPI()
                .servers(servers)
                .components(new Components())
                .info(new Info()
                        .title(apiName)
                        .description(apiDescription)
                        .version(apiVersion)
                        .contact(new Contact().name("For more details, see the documentation").url(apiUrlDoc))
                );
    }

    @Bean
    public String getVersion() {
        return version;
    }
}
