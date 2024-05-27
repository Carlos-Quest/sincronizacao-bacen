package br.com.sicredi.sincronizacao.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

@Configuration
public class MesssageConfiguration {

  @Bean
  public MessageSource messageSource() {
    Locale locale = new Locale("pt", "BR");
    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
    messageSource.setBasename("classpath:messages");
    messageSource.setDefaultLocale(locale);
    messageSource.setDefaultEncoding("UTF-8");
    return messageSource;
  }

}