package br.com.brewer.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import br.com.brewer.controller.CervejasController;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = { CervejasController.class })
public class WebConfig extends WebMvcConfigurerAdapter {
	
	

}
