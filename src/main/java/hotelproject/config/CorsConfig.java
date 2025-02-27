package hotelproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // ✅ 모든 경로 허용
                        .allowedOrigins("http://localhost:5173") // ✅ 프론트엔드 주소 허용
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowCredentials(true); // ✅ 세션 쿠키 허용
            }
        };
    }
}