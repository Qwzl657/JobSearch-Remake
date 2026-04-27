package kg.attractor.jobsearch_remake.config;

import kg.attractor.jobsearch_remake.service.impl.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .userDetailsService(userDetailsService)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .formLogin(form -> form
                        .loginPage("/auth/login")
                        .loginProcessingUrl("/auth/login")
                        .successHandler((request, response, authentication) -> {
                            var authorities = authentication.getAuthorities();
                            boolean isEmployer = authorities.stream()
                                    .anyMatch(a -> a.getAuthority().equals("ROLE_EMPLOYER"));
                            if (isEmployer) {
                                response.sendRedirect("/resumes/all");
                            } else {
                                response.sendRedirect("/vacancies");
                            }
                        })
                        .failureUrl("/auth/login?error=true")
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/auth/login")
                        .permitAll())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/avatars/**").permitAll()
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/vacancies/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/vacancies/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/users").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/resumes/**").hasAnyRole("APPLICANT", "EMPLOYER")
                        .requestMatchers(HttpMethod.POST, "/api/resumes/**").hasRole("APPLICANT")
                        .requestMatchers(HttpMethod.PUT, "/api/resumes/**").hasRole("APPLICANT")
                        .requestMatchers(HttpMethod.DELETE, "/api/resumes/**").hasRole("APPLICANT")
                        .requestMatchers(HttpMethod.POST, "/api/vacancies/**").hasRole("EMPLOYER")
                        .requestMatchers(HttpMethod.PUT, "/api/vacancies/**").hasRole("EMPLOYER")
                        .requestMatchers(HttpMethod.DELETE, "/api/vacancies/**").hasRole("EMPLOYER")
                        .requestMatchers(HttpMethod.GET, "/resumes/**").hasAnyRole("APPLICANT", "EMPLOYER")
                        .requestMatchers(HttpMethod.POST, "/resumes/**").hasRole("APPLICANT")
                        .requestMatchers(HttpMethod.PUT, "/resumes/**").hasRole("APPLICANT")
                        .requestMatchers(HttpMethod.DELETE, "/resumes/**").hasRole("APPLICANT")
                        .requestMatchers(HttpMethod.POST, "/vacancies/**").hasRole("EMPLOYER")
                        .requestMatchers(HttpMethod.PUT, "/vacancies/**").hasRole("EMPLOYER")
                        .requestMatchers(HttpMethod.DELETE, "/vacancies/**").hasRole("EMPLOYER")
                        .requestMatchers("/profile/**").authenticated()
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}