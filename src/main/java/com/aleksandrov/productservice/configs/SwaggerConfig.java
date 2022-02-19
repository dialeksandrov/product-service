package com.aleksandrov.productservice.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * @author dialeksandrov
 * @created 19/02/2022
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(metaData())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.aleksandrov.productservice.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo metaData(){
        return new ApiInfo("Test project for Setronica",
                           "Product Service",
                           "1.0", "Free to use",
                            new Contact("Dmitrii Aleksandrov", "https://www.linkedin.com/in/dmitrii-aleksandrov-a24252214/", "dialeksandrov12@gmail.com"),
                           "Api License",
                           "https://www.apache.org/licenses/LICENSE-2.0",
                            Collections.emptyList());
    }
}
