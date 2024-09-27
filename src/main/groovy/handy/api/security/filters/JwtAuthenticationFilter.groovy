package handy.api.security.filters

import handy.api.TokenService
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthenticationFilter extends OncePerRequestFilter{

    TokenService tokenService

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try{
            String authHeader = request.getHeader("Authorization")
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7)  // Quitar "Bearer "
                String email = tokenService.getSubject(token)
                println(email)
                if (email != null) {
                    Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, null)
                    SecurityContextHolder.getContext().setAuthentication(authentication)
                }
            }
            filterChain.doFilter(request, response)
        }catch (AuthenticationCredentialsNotFoundException e){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN)
        }catch (RuntimeException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED)
        }
    }

}
