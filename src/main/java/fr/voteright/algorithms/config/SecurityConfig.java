package fr.voteright.algorithms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final AuthGuard authGuard;

    public SecurityConfig(AuthGuard authGuard) {
        this.authGuard = authGuard;
    }

    /**
     * Configure la sécurité de l'application en appliquant la règle "AuthGuard" définie dans la classe du même nom.
     * La règle est appliquée sur l'ensemble des routes ("/**").
     * Le CSRF qui n'est généralement pas nécessaire pour un API REST est désactivé.
     * Le filtre par clé api est ajouté avant la verification du mot de passe.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable) // Désactiver CSRF "Cross Site Request Forgery"
            .addFilterBefore(authGuard, UsernamePasswordAuthenticationFilter.class)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/**").authenticated()
                .anyRequest().permitAll()
            );

        return http.build();
    }
}
