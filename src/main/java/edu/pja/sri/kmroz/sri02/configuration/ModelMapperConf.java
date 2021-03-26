package edu.pja.sri.kmroz.sri02.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConf {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
