package br.com.jujubaprojects.studensapi.Config;

import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;

@Configuration
public class SpringOpenApiConfig {
    
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI() // Inicializa um objeto OpenAPI
                .info(new Info() // Configura as informações gerais da API.
                     .title("students-api") // Define o título da API
                     .version("v1") // Define a versão da API
                     .description("API para gestão das médias dos ALunos universitários") // Define a descrição da API
                     .termsOfService("https://jujubaprojects.com.br/meus-cursos") // Define a URL dos termos de serviço da API
                     .license(new License() // Define as informações da licença
                         .name("Apache 1.0") // Define o nome da licença
                         .url("https://www.apache.org/licenses/LICENSE-1.0")) // Define a URL da licença
                     .contact(new Contact() // Define as informações de contato
                        .name("Junior Stranner") // Define o nome do contato
                        .email("Junior@spring-park.com")) // Define o e-mail do contato
                );
    }
}
