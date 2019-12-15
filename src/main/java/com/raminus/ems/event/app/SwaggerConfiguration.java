package com.raminus.ems.event.app;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Profile({"local", "test", "dev"})
public class SwaggerConfiguration {

  @Bean
  public Docket apiDocket() {
    return new Docket(DocumentationType.SWAGGER_2).
        select()
        .apis(RequestHandlerSelectors.basePackage("com.raminus.ems"))
        .paths(PathSelectors.ant("/private/**"))
        .build()
        .apiInfo(buildApiInfo());
  }

  protected ApiInfo buildApiInfo() {
    return new ApiInfo("Event Type Service", "Event Management Service API", "V1.0",
        "Works only in DEV. Disabled in QA and PROD",
        buildContact(), "Apache 2.0",
        "https://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());
  }

  private Contact buildContact() {
    return new Contact("raminus team", "www.raminus.com",
        "contact@raminus.com");
  }
}
