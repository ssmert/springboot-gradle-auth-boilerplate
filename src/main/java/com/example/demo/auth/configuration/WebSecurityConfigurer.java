package com.example.demo.auth.configuration;

import com.example.demo.auth.application.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
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

@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
    /**
     * 스프링 환경 제공자
     */
    @Autowired
    private Environment environment;

    /**
     * 로그인 서비스
     */
    @Autowired
    private AuthService authService;

    /**
     * 로그인 서비스 제공자
     */
    @Autowired
    private AuthProvider authProvider;

    /**
     * 인증되지 않은 접근에 대해 로그인 폼으로 redirect(302)시키지 않고 401 Status만 리턴
     */
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    /**
     * 인증관리자 빈
     *
     * @return 인증관리자 빈
     * @throws Exception 예외
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 인증필터 빈즈
     */
    @Bean
    public AuthTokenFilter authTokenFilterBean() throws Exception {
        AuthTokenFilter authTokenFilter = new AuthTokenFilter();
        authTokenFilter.setAuthenticationManager(this.authenticationManagerBean());

        return authTokenFilter;
    }

    /**
     * 비밀번호 인코더 빈즈
     *
     * @return 비밀번호 인코더
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new AuthPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 커스텀 인증서비스 주입
        auth.authenticationProvider(authProvider).userDetailsService(authService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // UTF-8로 캐릭터셋 필터 적용(한글 깨짐 방지)
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);

        // 캐릭터셋 필터를 csrf필터 전에 적용
        http.addFilterBefore(filter, CsrfFilter.class);
        // 인증필터 적용
        http.addFilterBefore(authTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

        // 로컬,개발,테스트환경만 Swagger2 접속 허용
        if (environment.acceptsProfiles(Profiles.of("loc | dev | test"))) {
            http.authorizeRequests().antMatchers("/swagger-**/**", "/configuration/**", "/v2/api-docs").permitAll();
        }

        // TODO .csrf().disable() 쿠키를 사용하지 않으면 csrf 방어 불필요?

        // CORS(Cross-Origin Resource Sharing : 타 도메인 간 자원 호출을 승인하거나 차단) 활성화
        http.cors().and()
                // CSRF(Cross Site Request Forgery : 사이트 간 요청 위조) 비활성화
                .csrf().disable()
                // X-Frame-Options 헤더 제거( iframe 사용 시 오류 방지)
                //                .headers().frameOptions().disable().and()
                // 인증 실패시 Redirect(302) 대신 401 Status만 리턴하도록 설정
                .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()
                // STATELESS : Security 인증 세션을 유지하지 않도록 설정
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
                // preflight(HttpMethod.OPTIONS) 요청 오류 방지
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                // static resources
                .antMatchers("/css/**", "/js/**", "/resources/**", "/webjars/**").permitAll();

        // TEST 설정
//        http.authorizeRequests().antMatchers("/**").permitAll()

        http.authorizeRequests()
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                // 공통영역 : REST 접근허가 역할별 정의
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                // 어플리케이션 (AppController) : 서버정보
                .antMatchers(HttpMethod.GET, "/server/info").permitAll()

                // 사용자 인증 (AuthController) : 사용자 인증, 사용자 인증 토큰 갱신
                .antMatchers(HttpMethod.POST, "/auth").permitAll()

                // ================================================================
                .anyRequest().authenticated();

    }
}
