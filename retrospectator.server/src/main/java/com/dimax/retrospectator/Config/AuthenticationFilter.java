//
//package com.dimax.retrospectator.Config;
//
////
////import com.appsdeveloperblog.ws.service.impl.AuthenticationServiceImpl;
////import com.appsdeveloperblog.ws.shared.dto.UserProfileDto;
////import com.appsdeveloperblog.ws.shared.exceptions.AuthenticationException;
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.annotation.Priority;
//import javax.ws.rs.NotAuthorizedException;
//import javax.ws.rs.Priorities;
//import javax.ws.rs.container.ContainerRequestContext;
//import javax.ws.rs.container.ContainerRequestFilter;
//import javax.ws.rs.core.HttpHeaders;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.ext.Provider;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.JWTVerifier;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.exceptions.JWTVerificationException;
//import com.auth0.jwt.interfaces.DecodedJWT;
//import com.dimax.retrospectator.Services.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationServiceException;
//import org.springframework.stereotype.Component;
//
//@Component
//@Secured
//@Provider
//@Priority(Priorities.AUTHENTICATION)
//public class AuthenticationFilter implements ContainerRequestFilter {
//    @Autowired
//    UserService usersService;
//
//
//
//    @Override
//    public void filter(ContainerRequestContext requestContext) throws IOException {
//        // Extract Authorization header details
//        String authorizationHeader
//                = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
//        System.out.println("dfsgdfg");
//        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer")) {
//            throw new NotAuthorizedException("Authorization header must be provided");
//        }
//        String token = authorizationHeader.substring("Bearer".length()).trim();
//        try {
//            // Validate the token
//            validateToken(token);
//        } catch (AuthenticationServiceException ex) {
//            Logger.getLogger(AuthenticationFilter.class.getName()).log(Level.SEVERE, null, ex);
//            requestContext.abortWith(
//                    Response.status(Response.Status.UNAUTHORIZED).build());
//        }
//    }
//
//    private void validateToken(String token) throws AuthenticationServiceException {
//
//        try {
//            Algorithm algorithm = Algorithm.HMAC256("secret");
//            JWTVerifier verifier = JWT.require(algorithm)
//                    .withIssuer("auth0")
//                    .build();
//            DecodedJWT jwt = verifier.verify(token);
//        } catch (UnsupportedEncodingException exception){
//        } catch (JWTVerificationException exception){
//        }
//
//
////
////
////
////
////        UserProfileDto userProfile = usersService.getUserProfileByPublicId(userId);
////        String completeToken = userProfile.getToken() + token;
////        String securePassword = userProfile.getUserPassword();
////        String salt = userProfile.getSalt();
////        String accessTokenMaterial = userId + salt;
////        byte[] encryptedAccessToken = null;
////        try {
////            encryptedAccessToken = authenticationUtil.encrypt(securePassword, accessTokenMaterial);
////        } catch (InvalidKeySpecException ex) {
////            Logger.getLogger(AuthenticationServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
////            throw new AuthenticationException("Faled to issue secure access token");
////        }
////        String encryptedAccessTokenBase64Encoded = Base64.getEncoder().encodeToString(encryptedAccessToken);
////        if (!encryptedAccessTokenBase64Encoded.equalsIgnoreCase(completeToken)) {
////            throw new AuthenticationServiceException("Authorization token did not match");
////        }
//    }
//}
//
