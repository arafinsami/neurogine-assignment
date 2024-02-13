package org.neurogine.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
public class OpenAPIConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(apiInfo());
    }

    @Bean
    public GroupedOpenApi privateApi() {
        return GroupedOpenApi.builder()
                .group("private-apis")
                .pathsToMatch("/**")
                .packagesToScan("org.neurogine.controller")
                .pathsToExclude("/actuator/**")
                .build();
    }

    private Info apiInfo() {
        return new Info()
                .title("API for Neurogine App web and mobile client")
                .description("API for Neurogine")
                .version("1.0")
                .contact(apiContact())
                .license(apiLicence());
    }

    private Contact apiContact() {
        return new Contact()
                .name("Neurogine")
                .email("info@org.neurogine")
                .url("https://www.neurogine.com/");
    }

    private License apiLicence() {
        return new License()
                .name("MIT Licence")
                .url("https://opensource.org/licenses/mit-license.php");
    }
}
