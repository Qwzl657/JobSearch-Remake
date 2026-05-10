package kg.attractor.jobsearch_remake.config;

import org.springframework.security.core.userdetails.UserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .userDetailsService(userService)
                .formLogin(form -> form
                        .loginPage("/auth/login")
                        .loginProcessingUrl("/auth/login")
                        .successHandler((request, response, authentication) -> {
                            boolean isEmployer = authentication.getAuthorities()
                                    .stream()
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
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .permitAll())
                .headers(headers -> headers
                        .frameOptions(frame -> frame.sameOrigin()))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/avatars/**").permitAll()
                        .requestMatchers("/favicon.ico").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()

                        .requestMatchers(HttpMethod.GET, "/vacancies").permitAll()
                        .requestMatchers(HttpMethod.GET, "/vacancies/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/vacancies/**").permitAll()

                        .requestMatchers(HttpMethod.POST, "/api/users").permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/resumes/**").hasAnyRole("APPLICANT", "EMPLOYER")
                        .requestMatchers(HttpMethod.POST, "/api/resumes/**").hasRole("APPLICANT")
                        .requestMatchers(HttpMethod.PUT, "/api/resumes/**").hasRole("APPLICANT")
                        .requestMatchers(HttpMethod.DELETE, "/api/resumes/**").hasRole("APPLICANT")

                        .requestMatchers(HttpMethod.POST, "/api/vacancies/**").hasRole("EMPLOYER")
                        .requestMatchers(HttpMethod.PUT, "/api/vacancies/**").hasRole("EMPLOYER")
                        .requestMatchers(HttpMethod.DELETE, "/api/vacancies/**").hasRole("EMPLOYER")

                        .requestMatchers(HttpMethod.GET, "/resumes/all").hasAnyRole("APPLICANT", "EMPLOYER")
                        .requestMatchers(HttpMethod.GET, "/resumes/create").hasRole("APPLICANT")
                        .requestMatchers(HttpMethod.POST, "/resumes/create").hasRole("APPLICANT")
                        .requestMatchers(HttpMethod.GET, "/resumes/*/edit").hasRole("APPLICANT")
                        .requestMatchers(HttpMethod.POST, "/resumes/*/edit").hasRole("APPLICANT")

                        .requestMatchers(HttpMethod.POST, "/resumes/*/delete").hasRole("APPLICANT")
                        .requestMatchers(HttpMethod.POST, "/vacancies/*/delete").hasRole("EMPLOYER")

                        .requestMatchers(HttpMethod.POST, "/vacancies/create").hasRole("EMPLOYER")
                        .requestMatchers(HttpMethod.GET, "/vacancies/create").hasRole("EMPLOYER")
                        .requestMatchers(HttpMethod.GET, "/vacancies/*/edit").hasRole("EMPLOYER")
                        .requestMatchers(HttpMethod.POST, "/vacancies/*/edit").hasRole("EMPLOYER")

                        .requestMatchers(HttpMethod.GET, "/vacancies/*/respond").hasRole("APPLICANT")
                        .requestMatchers(HttpMethod.POST, "/vacancies/*/respond").hasRole("APPLICANT")
                        .requestMatchers(HttpMethod.GET, "/vacancies/*/responses").hasRole("EMPLOYER")

                        .requestMatchers(HttpMethod.GET, "/employers").hasRole("APPLICANT")

                        .requestMatchers("/profile/**").authenticated()
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}