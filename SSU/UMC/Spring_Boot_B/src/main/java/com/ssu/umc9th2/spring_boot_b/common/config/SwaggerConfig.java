package com.ssu.umc9th2.spring_boot_b.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                // Swagger Authorize 버튼 누르면 모든 요청에 Bearer 헤더 자동 포함
                .info(new Info()
                        .title("UMC Backend API")
                        .description("UMC 9기 B조 BLUE의  백엔드 API 문서입니다.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("UMC 9TH SpringBoot Team_B")
                                .email("pooreumjung02@naver.coom"))
                        .license(new License()
                                .name("Apache License 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}
