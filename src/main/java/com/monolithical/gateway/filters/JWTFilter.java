package com.monolithical.gateway.filters;

import com.monolithical.gateway.services.jwt.JWTValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class JWTFilter extends OncePerRequestFilter {

    @Value("#{'${filter.url.exclude}'.split(',')}")
    private List<String> excludes;
    private final String errorUnauthorizedFormat = "Unauthorized: %s";
    private final JWTValidator validator;
    private AntPathMatcher pathMatcher;

    public JWTFilter(JWTValidator validator) {
        this.validator = validator;
        this.pathMatcher = new AntPathMatcher();
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain)
            throws ServletException, IOException {
        String header = httpServletRequest.getHeader("Authorization");
        if (header != null) {
            String[] content = httpServletRequest.getHeader("Authorization").split(" ");

            if (content[0].equals("Bearer") && validator.isValid(content[1])) {
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            } else {
                httpServletResponse.sendError(
                        HttpServletResponse.SC_UNAUTHORIZED,
                        String.format(errorUnauthorizedFormat, "The token is not valid."));
            }
        } else {
            httpServletResponse.sendError(
                    HttpServletResponse.SC_UNAUTHORIZED,
                    String.format(errorUnauthorizedFormat, "Authorization header is missing."));
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return excludes.stream().anyMatch(path -> pathMatcher.match("/" + path, request.getServletPath()));
    }
}
