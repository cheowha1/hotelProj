package hotelproject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

	 private final SessionInterceptor sessionInterceptor;

	    public WebConfig(SessionInterceptor sessionInterceptor) {
	        this.sessionInterceptor = sessionInterceptor;
	    }

	    @Override
	    public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**")
	                .allowedOrigins("http://localhost:5173")
	                .allowedMethods("GET", "POST", "PUT", "DELETE")
	                .allowCredentials(true);
	    }

	    @Override
	    public void addInterceptors(InterceptorRegistry registry) {
	        registry.addInterceptor(sessionInterceptor) // 오류 해결: 올바른 Bean 사용
	                .addPathPatterns("/users/**", "/reservations/**", "/reviews/**", "/admin/**")
	                .excludePathPatterns("/users/login", "/users/register");
	    }
}
