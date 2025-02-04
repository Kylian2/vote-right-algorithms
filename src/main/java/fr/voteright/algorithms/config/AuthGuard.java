package fr.voteright.algorithms.config;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.List;

@Component
public class AuthGuard extends GenericFilterBean {

    private final String apiKey;

    public AuthGuard() {
        Dotenv dotenv = Dotenv.load();
        this.apiKey = dotenv.get("KEY");
    }

    /**
     * Gère l'authentification de l'application via une clé api définie dans les paramètre d'environnement (.env).
     * Si la clé est bonne, on ouvre une session pour que Spring Security laisse passer les requêtes issues du contexte puis on laisse
     * la requête se poursuivre.
     * Sinon la clé est incorrecte on renvoie une erreur HTTP 401 Unauthorized.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestApiKey = httpRequest.getHeader("X-API-KEY");

        if (requestApiKey == null || !requestApiKey.equals(apiKey)) {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Clé API invalide ou absente");
            return;
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                "api-user",
                null,
                List.of(new SimpleGrantedAuthority("ROLE_API_USER"))
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request, response);
    }
}
