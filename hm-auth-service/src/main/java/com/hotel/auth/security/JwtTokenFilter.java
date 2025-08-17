package com.hotel.auth.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

//	@Autowired
//	JwtTokenUtil jwtTokenUtil;
//
//	@Autowired
//	UserRepository userRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

//        final String requestTokenHeader = request.getHeader("Authorization");
//        String mobileNumber = null;
//        String jwtToken = null;
//
//        // JWT Token is in the form "Bearer token". Remove Bearer word and get only the Token
//        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
//            jwtToken = requestTokenHeader.substring(7);
//            try {
//                mobileNumber = jwtTokenUtil.getMobileNumberFromToken(jwtToken);
//            } catch (Exception e) {
//                logger.error("Unable to get JWT Token", e);
//            }
//        } else {
//            logger.warn("JWT Token does not begin with Bearer String");
//        }
//
//        // Once we get the token validate it and set authentication
//        if (mobileNumber != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//        	final String token = jwtToken;
//        	userRepository.findByMobileNumber(mobileNumber).ifPresent(user -> {
//                if (jwtTokenUtil.validateToken(token)) {
//                    UsernamePasswordAuthenticationToken authentication = 
//                        new UsernamePasswordAuthenticationToken(
//                            user, null, null);
//                    
//                    authentication.setDetails(
//                        new WebAuthenticationDetailsSource().buildDetails(request)
//                    );
//                    
//                    SecurityContextHolder.getContext().setAuthentication(authentication);
//                }
//            });
//        }

		chain.doFilter(request, response);

	}
}