package es.udc.lbd.asi.restexample.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import es.udc.lbd.asi.restexample.security.JWTConfigurer;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import es.udc.lbd.asi.restexample.security.TokenProvider;
import es.udc.lbd.asi.restexample.security.MyAccessDeniedHandler;
import es.udc.lbd.asi.restexample.security.MyUnauthorizedEntryPoint;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.BeanInitializationException;
import es.udc.lbd.asi.restexample.security.MyUserDetailsService;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private Properties properties;
	
	@Autowired
    private MyUserDetailsService myUserDetailsService;
	
	 @Autowired
     private MyUnauthorizedEntryPoint myUnauthorizedEntryPoint;

     @Autowired
     private MyAccessDeniedHandler myAccessDeniedHandler;

     @Autowired
     private TokenProvider tokenProvider;
	
	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }




	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	 @Autowired
     public void configureAuth(AuthenticationManagerBuilder auth) {
         try {
             auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
         } catch (Exception e) {
             throw new BeanInitializationException("SecurityConfiguration.configureAuth failed", e);
         }
     }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .csrf()
        .disable()
        .exceptionHandling()
        .authenticationEntryPoint(myUnauthorizedEntryPoint)
        .accessDeniedHandler(myAccessDeniedHandler)
    .and()
            .headers().frameOptions().disable()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
            .authorizeRequests()
        .antMatchers("/api/authenticate").permitAll()
        .antMatchers(HttpMethod.POST, "/api/register").permitAll()
        .antMatchers(HttpMethod.GET, "/api/movies/**").permitAll()
        .antMatchers(HttpMethod.GET, "/api/users/LoginEmail").permitAll()
        .antMatchers("/**").authenticated()
    .and()
        .apply(securityConfigurerAdapter());;
            
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
            	registry.addMapping("/**").allowedMethods("*").allowedOrigins(properties.getClientHost());
            }
        };
    }
    
    private JWTConfigurer securityConfigurerAdapter() {
        return new JWTConfigurer(tokenProvider);
    }
}
