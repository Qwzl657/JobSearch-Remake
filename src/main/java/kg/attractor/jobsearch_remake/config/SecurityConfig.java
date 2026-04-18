package kg.attractor.jobsearch_remake.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JdbcUserDetailsManager userDetailsManager() {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);

        manager.setUsersByUsernameQuery(
                "SELECT email, password, enabled FROM users WHERE email = ?"
        );

        manager.setAuthoritiesByUsernameQuery(
                "SELECT u.email, r.role FROM users u " +
                        "JOIN roles r ON u.role_id = r.id " +
                        "WHERE u.email = ?"
        );

        return manager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(httpBasic -> {})
                .authorizeHttpRequests(auth -> auth


                        .requestMatchers(HttpMethod.POST, "/users").permitAll()


                        .requestMatchers(HttpMethod.GET, "/vacancies/**").permitAll()


                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()


                        .requestMatchers(HttpMethod.GET, "/resumes/**").hasAnyRole("APPLICANT", "EMPLOYER")
                        .requestMatchers(HttpMethod.POST, "/resumes/**").hasRole("APPLICANT")
                        .requestMatchers(HttpMethod.PUT, "/resumes/**").hasRole("APPLICANT")
                        .requestMatchers(HttpMethod.DELETE, "/resumes/**").hasRole("APPLICANT")

                        .requestMatchers(HttpMethod.POST, "/vacancies/**").hasRole("EMPLOYER")
                        .requestMatchers(HttpMethod.PUT, "/vacancies/**").hasRole("EMPLOYER")
                        .requestMatchers(HttpMethod.DELETE, "/vacancies/**").hasRole("EMPLOYER")


                        .requestMatchers(HttpMethod.POST, "/vacancies/*/respond").hasRole("APPLICANT")


                        .requestMatchers(HttpMethod.GET, "/users/**").authenticated()


                        .anyRequest().authenticated()
                );

        return http.build();
    }
}