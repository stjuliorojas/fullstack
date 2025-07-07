package secur.com.example.app_aut.Security;

import org.springframework.beans.factory.annotation.Autowired;
import secur.com.example.app_aut.Service.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    private final String HEADER = "Authorization";
    private final String PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal (HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String path=request.getRequestURI();

        if(path.startsWith("/auth")){
            chain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader(HEADER);
        if(authHeader != null && authHeader.startsWith(PREFIX)){
            String jwt=authHeader.substring(PREFIX.length());
            String username= jwtUtil.extractUsername(jwt);
            if(username != null && SecurityContextHolder.getContext().getAuthentication()==null){
                UserDetails userDetails=userDetailsService.loadUserByUsername(username);
                if(jwtUtil.validateToken(jwt, userDetails)){
                    UsernamePasswordAuthenticationToken token=
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(token);
                }
            }
        }
        chain.doFilter(request, response);
    }
}
