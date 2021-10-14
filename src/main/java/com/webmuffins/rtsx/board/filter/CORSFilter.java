package com.webmuffins.rtsx.board.filter;

import static com.webmuffins.rtsx.board.constants.HTTPConstants.*;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CORSFilter implements Filter {

    @Value("#{'${allowed.origin.paths}'.split(',')}")
    private List<String> origins;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException,
            ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        setResponseHeadersProperties(response);
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if(!checkValidOrigin(request)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        if (HttpMethod.OPTIONS.name().equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    private boolean checkValidOrigin(HttpServletRequest request) {
        return origins.stream()
                .anyMatch(origin -> request.getRemoteHost().contains(origin));
    }

    private void setResponseHeadersProperties(HttpServletResponse response) {
        getOrigins().forEach(origin -> response.setHeader(CORS_ORIGIN_HEADER_PATTERN, origin));
        response.setHeader(CORS_METHODS_HEADER_PATTERN, ALLOWED_HTTP_METHODS);
        response.setHeader(CORS_HEADERS_HEADER_PATTERN, "*");
        response.setHeader(CORS_REQUEST_MAX_AGE_PATTERN, ALLOWED_MAX_AGE);
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    List<String> getOrigins() {
        return origins;
    }

    void setOrigins(List<String> origins) {
        this.origins = origins;
    }

}
