package org.gfg.expenseTracker.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("My API")
                        .version("1.0")
                        .description("This is a sample Spring Boot RESTful service using springdoc-openapi and OpenAPI 3.")
                        .contact(new Contact()
                                .name("Your Name")
                                .email("your.email@example.com")
                                .url("https://example.com")));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public-api")
                .pathsToMatch("/api/**")
                .build();
    }
}
//public class SwaggerConfig {
//
//    @Bean
//    public Docket apiDoc() {
//
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.exam.controller"))
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//}

