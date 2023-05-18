package com.Telecom.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class Swagger3Config {

    @Bean
    public Docket docket(){
        //解读：
        //1. 链式编程(构造器模式 / 级联方程式)
        //2. OAS_30 表示使用 Swagger3 (SWAGGER_12表示使用Swagger1.2, SWAGGER_2表示使用Swagger2.0)
        //3. .apiInfo 用于指定api接口文档的信息 ()中传入 创建ApiInfo的方法名(在下面定义)
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(createApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.Telecom.controller"))
                .build();
    }

    //创建api的基本信息
    private ApiInfo createApiInfo(){
        //链式编程(构造器模式 / 级联方程式)
        return new ApiInfoBuilder()
                .title("备份服务接口文档")
                .description("SpringBoot集成Swagger3构建RESTful APIs")
                .version("3.0")
                .contact(new Contact("Godfrey","https://github.com/MassieOOP","2298227380@qq.com"))
                .version("1.0.0")
                .license("Apache 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .build();

    }
}
