package com.example.demo.core.config;

import com.example.demo.auth.application.AuthUserDetailsService;
import com.example.demo.auth.config.AuthPasswordEncoder;
import com.example.demo.auth.config.AuthProvider;
import com.example.demo.auth.config.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * WebSecurity 설정
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final Environment environment;
    private final AuthUserDetailsService authUserDetailsService;
    private final AuthProvider authProvider;
    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    private final JwtFilter jwtFilter;
    private final ExceptionHandlerFilter exceptionHandlerFilter;

    /**
     * 인증관리자
     *
     * @return 인증관리자
     *
     * @exception Exception 예외
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 비밀번호 인코더
     *
     * @return 비밀번호 인코더
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new AuthPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider).userDetailsService(authUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // UTF-8로 캐릭터셋 필터 적용(한글 깨짐 방지)
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);

        // Swagger2 접속 허용(로컬,개발,테스트)
        if (environment.acceptsProfiles(Profiles.of("loc | dev | test"))) {
            http.authorizeRequests().antMatchers("/swagger-**/**", "/configuration/**", "/v3/api-docs").permitAll();
        }

        // CORS(Cross-Origin Resource Sharing : 타 도메인 간 자원 호출을 승인하거나 차단) 활성화
        http.cors();
        // CSRF(Cross Site Request Forgery : 사이트 간 요청 위조) 비활성화
        http.csrf().disable();
        // 인증 실패시 Redirect(302) 대신 401 Status만 리턴하도록 설정
        http.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint);
        // STATELESS : Security 인증 세션을 유지하지 않도록 설정
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // 캐릭터셋 필터
        http.addFilterBefore(characterEncodingFilter, CsrfFilter.class);
        // 토큰 인증 필터
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        // 필터 에러 핸들러
        http.addFilterBefore(exceptionHandlerFilter, JwtFilter.class);

        http.authorizeRequests()
                // preflight(HttpMethod.OPTIONS) 요청 오류 방지
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                // static resources
                .antMatchers("/css/**", "/js/**", "/resources/**", "/webjars/**").permitAll();

        // TEST 설정
        //        http.authorizeRequests().antMatchers("/**").permitAll();

        // 역할별 접근 설정
        http.authorizeRequests()
                // 앱
                .antMatchers(HttpMethod.GET, "/server/info").permitAll()
                // 인증
                .antMatchers(HttpMethod.POST, "/auth/login").permitAll()
                // 모든 요청 인증 확인
                .anyRequest().authenticated();

    }
}
