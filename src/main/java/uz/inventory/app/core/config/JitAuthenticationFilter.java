package uz.inventory.app.core.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.inventory.app.auth.service.UserDTLService;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JitAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDTLService userDTLService;


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        try {
            String jwt = getJwtFromRequest(request);
            if (!StringUtils.hasText(jwt)) {
                filterChain.doFilter(request, response);
                return;
            }
            if (StringUtils.hasText(jwt)) {
                String user = jwtService.extractUsername(jwt);
                UserDetails userDetails = this.userDTLService.loadUserByUsername(user);
                if (jwtService.isTokenValid(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails, userDetails.getPassword(), userDetails.getAuthorities()
                    );
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    filterChain.doFilter(request, response);
                    return;
                }
            }
        } catch (Exception ex) {

//            ex.printStackTrace();
            logger.error("Could not set user authentication in security context", ex);
        }
        filterChain.doFilter(request, response);
    }


    private String getJwtFromRequest(ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String bearerToken = httpRequest.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

}
