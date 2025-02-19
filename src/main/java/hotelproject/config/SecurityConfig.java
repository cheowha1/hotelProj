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
                .requestMatchers("/admin/**").authenticated() //  관리자 API 보호
                .requestMatchers("/reservation/history").authenticated() //  로그인한 사용자만 예약 내역 조회 가능
            )
            .formLogin(login -> login
                .defaultSuccessUrl("/main", true) // 로그인 성공 시 메인 페이지로 이동
                .permitAll()
            )
            .logout(logout -> logout.permitAll());

        return http.build();
    }
}