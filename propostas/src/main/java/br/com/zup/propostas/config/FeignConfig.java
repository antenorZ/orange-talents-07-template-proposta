package br.com.zup.propostas.config;


import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class FeignConfig {
    @Bean
    public Logger.Level retornaLog(){
        return Logger.Level.FULL;
    }
}
