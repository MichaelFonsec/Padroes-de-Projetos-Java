package one.digitalinnovation.labpadroesprojetospring.doc;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class ApiConfig {
        @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("springdoc")
                .packagesToScan("one.digitalinnovation.labpadroesprojetospring.controller")
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Client API")
                                .description("API desenvolvido para Lab do DIO")
                                .version("1.0.0")
                                .termsOfService("http://termosdeservico.com")
                                .contact(new Contact().name("Michael Martins")
                                                      .url("http://url-do-contato.com")
                                                      .email("email@contato.com"))
                                .license(new License().name("Primeira Licença")
                                                       .url("http://url-da-licença.com")));
    }
}


