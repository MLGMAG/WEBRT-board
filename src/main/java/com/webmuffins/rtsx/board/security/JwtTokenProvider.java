package com.webmuffins.rtsx.board.security;


import static com.webmuffins.rtsx.board.constants.HTTPConstants.AUTHORIZATION_HEADER;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.webmuffins.rtsx.board.constants.Role;
import com.webmuffins.rtsx.board.exception.InvalidTokenException;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.validity-time}")
    private long validityTime;


        public void validateToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            validateTokenExpiration(claimsJws);
        } catch (Exception e) {
            throw new InvalidTokenException(e.getMessage());
        }
    }

    private void validateTokenExpiration(Jws<Claims> claimsJws) {
        boolean before = claimsJws.getBody().getExpiration().before(new Date());
        if(before) {
            throw new InvalidTokenException("Token is expired");
        }
    }

    public Authentication getAuthentication(String token) {
        UserPrincipal userDetails = getUserPrincipalFromToken(token);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getRole().getAuthorities());
    }

    private UserPrincipal getUserPrincipalFromToken(String token) {
        Claims body = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        String email = body.getSubject();
        Role role = Role.getRoleByName((String) body.get("role"));
        return new UserPrincipal(email, role);
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader(AUTHORIZATION_HEADER);
    }

}
