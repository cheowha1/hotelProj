package hotelproject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**") // 모든 API 허용
				.allowedOrigins("http://localhost:5173") //React 프론트엔드 허용
				.allowedMethods("GET", "POST", "PUT", "DELETE") // HTTP 허용 메서드
				.allowCredentials(true);
	}
}
