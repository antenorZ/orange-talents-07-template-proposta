package br.com.zup.propostas.config;


import feign.Logger;
import org.springframework.context.annotation.Bean;


public class FeignConfig {
    @Bean
    public Logger.Level retornaLog(){
        return Logger.Level.FULL;
    }
}
