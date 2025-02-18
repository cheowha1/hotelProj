package hotelproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	// 비밀번호 암호화
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // CSRF 비활성화 (API 사용 시 필요)
            .authorizeHttpRequests(auth -> auth
            	.requestMatchers("/user/signup", "/user/login").permitAll()  // 회원가입 및 로그인 API 허용
                .requestMatchers("/api/**").authenticated() // 나머지 API는 인증 필요
            )
            .formLogin(login -> login
                .defaultSuccessUrl("/home", true)
                .permitAll()
            )
            .logout(logout -> logout.permitAll());

        return http.build();
    }
}
