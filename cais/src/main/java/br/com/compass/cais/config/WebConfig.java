package br.com.compass.cais.config;

import br.com.compass.cais.services.StringEnumConvertOrigin;
import br.com.compass.cais.services.StringEnumConverteStatus;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringEnumConvertOrigin());
        registry.addConverter(new StringEnumConverteStatus());
    }
}
