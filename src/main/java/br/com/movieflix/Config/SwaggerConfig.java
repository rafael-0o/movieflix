package br.com.movieflix.Config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Marca uma classe como uma fonte de definições de beans
@Configuration
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer"
)
public class SwaggerConfig {
    /**Um bean é um objeto que é instanciado, montado e gerenciado pelo contêiner Spring. Em vez de você
     controlar a criação e o ciclo de vida dos seus objetos, você delega essa responsabilidade para o framework**/

    @Bean
    public OpenAPI getOpenAPI(){
        Contact contact = new Contact();

        contact.name("Rafael");
        contact.email("rafael@email");
        Info info = new Info();
        info.title("Movieflix");
        info.version("v1");
        info.description("Api para gerenciamento de catalogo de filmes");
        info.contact(contact);
        return new OpenAPI().info(info);
    }
}
