package com.webmuffins.rtsx.board.filter;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CORSFilterTest {

    private static final String DEFAULT_ORIGIN = "/origin";
    private static final List<String> DEFAULT_ORIGINS = Collections.singletonList(DEFAULT_ORIGIN);
    private static final String DEFAULT_HTTP_REQUEST_METHOD = "OPTIONS";

    @Mock
    private HttpServletRequest servletRequest;

    @Mock
    private HttpServletResponse servletResponse;

    @Mock
    private FilterChain filterChain;

    @InjectMocks
    private CORSFilter testInstance;

    @BeforeEach
    void setUp() {
        testInstance.setOrigins(DEFAULT_ORIGINS);
    }

    @Test
    void shouldPassRequestOnDoFilter() throws ServletException, IOException {
        when(servletRequest.getMethod()).thenReturn(DEFAULT_HTTP_REQUEST_METHOD);
        when(servletRequest.getRemoteHost()).thenReturn(DEFAULT_ORIGIN);

        testInstance.doFilter(servletRequest, servletResponse, filterChain);

        verify(servletResponse).setStatus(HttpServletResponse.SC_OK);
    }

    @Test
    void shouldNotPassRequestOnDoFilter()  throws ServletException, IOException {
        when(servletRequest.getRemoteHost()).thenReturn("invalid origin");

        testInstance.doFilter(servletRequest, servletResponse, filterChain);

        verify(servletResponse).setStatus(HttpServletResponse.SC_FORBIDDEN);
    }

}
