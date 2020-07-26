package ca.sait.web7.config;

import java.util.ArrayList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static springfox.documentation.builders.PathSelectors.regex;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.service.Contact;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket productApi()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ca.sait.web7"))
                .paths(regex("/v1.*"))
                .build()
                //                .globalOperationParameters(
                //                        Collections.singletonList(new ParameterBuilder()
                //                                .name("Authorization")
                //                                .description("Bearer Token")
                //                                .modelRef(new ModelRef("string"))
                //                                .parameterType("header")
                //                                .required(true)
                //                                .build()))
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo()
    {

        ApiInfo apiInfo = new ApiInfo(
                "Primeira API RESt no swagger",
                "Estudando API usando o swagger",
                "1.0",
                "Terms of Service",
                new Contact("Paulo Alves",
                        "https://www.youtube.com/xxx",
                        "xxx@gmail.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/licesen.html", new ArrayList<VendorExtension>()
        );

        return apiInfo;
    }

}
