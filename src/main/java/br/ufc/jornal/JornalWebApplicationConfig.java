package br.ufc.jornal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import br.ufc.jornal.interceptor.InterceptorGeneral;

@Configuration
public class JornalWebApplicationConfig extends WebMvcConfigurerAdapter{
	
	@Autowired
	private InterceptorGeneral interceptorGeneral;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptorGeneral);
	}
	
}
