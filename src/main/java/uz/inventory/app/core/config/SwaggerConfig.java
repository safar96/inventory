package uz.inventory.app.core.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@OpenAPIDefinition
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        Server server = new Server();
        server.setUrl("http://localhost:223");
        server.setDescription("Development");

        Contact myContact = new Contact();
        myContact.setName("Safarmurod Musulmonov");
        myContact.setEmail("safarmurodmusulmonov@gmail.com");

        Info information = new Info()
                .title("Inventarizatsiya API")
                .version("1.0")
                .description("Bu korxonalarning jihozlarini inventarizatsiyadan o'tkazish bo'yicha api dokument")
                .contact(myContact);

        return new OpenAPI().info(information).servers(List.of(server));
    }
}