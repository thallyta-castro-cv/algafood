package br.com.thallyta.algafood.core.openapi.config;

import br.com.thallyta.algafood.core.openapi.RequestControllerOpenApi;
import br.com.thallyta.algafood.core.openapi.models.KitchensModelOpenApi;
import br.com.thallyta.algafood.core.openapi.models.PageableModelApi;
import br.com.thallyta.algafood.models.adapters.LogExceptionAdapter;
import br.com.thallyta.algafood.models.dtos.responses.KitchenResponseDTO;
import br.com.thallyta.algafood.models.dtos.responses.RequestSummaryResponseDTO;
import com.fasterxml.classmate.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.ServletWebRequest;
import springfox.documentation.builders.*;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Response;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@Configuration
public class SpringFoxConfig {

    @Bean
    public Docket apiDocket() {
        var typeResolver = new TypeResolver();

        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.thallyta.algafood.controllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .globalResponses(HttpMethod.GET, globalGetResponseMessages())
                .globalResponses(HttpMethod.POST, globalPostPutResponseMessages())
                .globalResponses(HttpMethod.PUT, globalPostPutResponseMessages())
                .globalResponses(HttpMethod.DELETE, globalDeleteResponseMessages())
                .additionalModels(typeResolver.resolve(LogExceptionAdapter.class))
                .ignoredParameterTypes(ServletWebRequest.class)
                .directModelSubstitute(Pageable.class, PageableModelApi.class)
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(Page.class, KitchenResponseDTO.class),
                        KitchensModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(Page.class, RequestSummaryResponseDTO.class),
                        RequestControllerOpenApi.class))
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .tags(new Tag("Cidades", "Gerencia as cidades"),
                      new Tag("Grupos", "Gerencia os grupos de usuários"),
                      new Tag("Cozinhas", "Gerencia as cozinhas"),
                      new Tag("Formas de pagamento", "Gerencia as formas de pagamento"),
                      new Tag("Pedidos", "Gerencia os pedidos"),
                      new Tag("Restaurantes", "Gerencia os restaurantes"),
                      new Tag("Estados", "Gerencia os estados"),
                      new Tag("Produtos", "Gerencia os produtos de restaurantes"),
                      new Tag("Usuários", "Gerencia os usuários"),
                      new Tag("Estatísticas", "Estatísticas de vendas"));
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Algafood Api")
                .description("API aberta para clientes e restaurantes")
                .version("1")
                .build();
    }

    private List<Response> globalGetResponseMessages() {
        return Arrays.asList(
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .description("Erro interno do Servidor")
                        .representation( MediaType.APPLICATION_JSON )
                        .apply(getLogExceptionAdapterModelReference())
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
                        .description("Recurso não possui representação que pode ser aceita pelo consumidor")
                        .build()
        );
    }

    private List<Response> globalPostPutResponseMessages() {
        return Arrays.asList(
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                        .description("Requisição inválida (erro do cliente)")
                        .representation( MediaType.APPLICATION_JSON )
                        .apply(getLogExceptionAdapterModelReference())
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .description("Erro interno no servidor")
                        .representation( MediaType.APPLICATION_JSON )
                        .apply(getLogExceptionAdapterModelReference())
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
                        .description("Recurso não possui representação que poderia ser aceita pelo consumidor")
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()))
                        .description("Requisição recusada porque o corpo está em um formato não suportado")
                        .representation( MediaType.APPLICATION_JSON )
                        .apply(getLogExceptionAdapterModelReference())
                        .build()
        );
    }

    private List<Response> globalDeleteResponseMessages() {
        return Arrays.asList(
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                        .description("Requisição inválida (erro do cliente)")
                        .representation( MediaType.APPLICATION_JSON )
                        .apply(getLogExceptionAdapterModelReference())
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .description("Erro interno no servidor")
                        .representation( MediaType.APPLICATION_JSON )
                        .apply(getLogExceptionAdapterModelReference())
                        .build()
        );
    }

    private Consumer<RepresentationBuilder> getLogExceptionAdapterModelReference() {
        return r -> r.model(m -> m.name("Exception Adapter Response")
                .referenceModel(ref -> ref.key(k -> k.qualifiedModelName(
                        q -> q.name("Exception Adapter Response").namespace("br.com.thallyta.algafood.models.adapters")))));
    }

}
