package hotelproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	 @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	  @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	            .csrf(csrf -> csrf.disable())  // CSRF 보호 비활성화
	            .authorizeHttpRequests(auth -> auth
	                .requestMatchers("/users/login", "/users/register").permitAll() // 로그인 & 회원가입은 인증 없이 접근 가능
	                .requestMatchers("/admin/**").hasRole("ADMIN") // 어드민 페이지는 ADMIN 권한 필요
	                .anyRequest().authenticated() // 나머지 요청은 로그인 필요
	            )
	            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)) // 세션 관리 설정
	            .formLogin(form -> form.disable()) // 기본 로그인 폼 비활성화
	            .logout(logout -> logout
	                .logoutUrl("/users/logout")
	                .logoutSuccessHandler((request, response, authentication) -> {
	                    request.getSession().invalidate();
	                    response.setStatus(200);
	                })
	            );

	        return http.build();
	    }
}