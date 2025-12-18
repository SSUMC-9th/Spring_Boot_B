package com.example.umc9th.infra.config;


import com.example.umc9th.infra.security.CustomUserDetailsService;
import com.example.umc9th.infra.security.entrypoint.AuthenticationEntryPointImpl;
import com.example.umc9th.infra.security.jwt.JwtAuthFilter;
import com.example.umc9th.infra.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//Spring Security 설정 활성화(기본 설정 보다 커스텀 설정이 먼저 적용됨)
@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;
    //허용 URI 따로 뺴서 관리
    private final String[] allowUris = {
            // Swagger 허용
            "/member/login",
            "/member/sign-up",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
    };

    //  커스텀 filter chain 설정(HttpSecurity로 구체화)
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(allowUris).permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                // 폼로그인 비활성화
                .formLogin(AbstractHttpConfigurer::disable)
                // JwtAuthFilter를 UsernamePasswordAuthenticationFilter 앞에 추가
                .addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                //에외 처리 추가
                .exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint()));


        return http.build();
    }
//        아래는 세션 방식(사용 시 위 중괄호 안에 넣기
//        http
//                //Http 요청 접근 제 설정
//                .authorizeHttpRequests(requests -> requests
//                                //특정 URL 패턴에 대한 접근 권한을 설정
//                                .requestMatchers(allowUris).permitAll()
////                        ADMIN' 역할을 가진 사용자만 접근 가능하도록 제한
//                                .requestMatchers("/admin/**").hasRole("ADMIN")
////                        그 외 모든 요청에 대해 인증을 요구
//                                .anyRequest().authenticated()
//                )
//                .formLogin(form -> form
//                                //로그인 성공 시 리다이렉트할 주소
//                                .defaultSuccessUrl("/swagger-ui/index.html", true)
////                        인증 없이 접근 가능한 경로를 지정(로그인 페이지는 모든 사용자가 접근 가능하도록)
//                                .permitAll()
//                )
//                //csrf disable
//                .csrf(AbstractHttpConfigurer::disable)
//                .logout(logout -> logout
//                        .logoutUrl("/logout")
//                        //로그아웃 성공시 이동할 주소
//                        .logoutSuccessUrl("/login?logout")
//                        .permitAll()
//                );
//
//        return http.build();

    //JWTAuthFilter 생성 -> 토큰 기반 사용
    @Bean
    public JwtAuthFilter jwtAuthFilter() {
        return new JwtAuthFilter(jwtUtil, customUserDetailsService);
    }
    //passwordEncoder Bean 등록 -> 세션 형식에서 사용
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new AuthenticationEntryPointImpl();
    }
}