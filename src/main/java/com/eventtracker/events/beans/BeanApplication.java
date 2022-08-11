package com.eventtracker.events.beans;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BeanApplication {
    
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
