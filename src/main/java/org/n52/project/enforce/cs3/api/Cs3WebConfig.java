package org.n52.project.enforce.cs3.api;

import java.util.List;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>
 * WebConfig class.
 * </p>
 *
 * @author Benjamin Pross (b.pross@52north.org)
 * @since 1.0.0
 */
@Configuration
public class Cs3WebConfig implements WebMvcConfigurer {

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new Cs3ExcelFileMessageConverter());
    }
    
    @Bean
    GroupedOpenApi cs3Apis() {
      return GroupedOpenApi.builder().group("cs3").pathsToMatch("/cs3_themis_data/**").build();
    }
}
