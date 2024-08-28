package com.security.ContactApp.security;



import com.security.ContactApp.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors((cors->cors.disable()))
                .csrf((csrf->csrf.disable()))
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/html/home.html").authenticated()
                                .requestMatchers("/html/viewcontacts.html").authenticated()
                                .requestMatchers("/html/addcontact.html").authenticated()
                                .anyRequest().permitAll() // All requests require authentication
                ).formLogin(formLogin ->
                        formLogin
                                .loginPage("/html/login.html")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/html/home.html", true) // Redirect to home page on success
                                .permitAll()
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/logout") // URL to trigger logout
                                .logoutSuccessUrl("/html/login.html") // Redirect to login page after logout
                                .invalidateHttpSession(true) // Invalidate session
                                .deleteCookies("JSESSIONID") // Delete session cookies
                                .permitAll()
                );
        return http.build();
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
