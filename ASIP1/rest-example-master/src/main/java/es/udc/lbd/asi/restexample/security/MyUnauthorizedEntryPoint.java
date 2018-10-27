package es.udc.lbd.asi.restexample.security;

 import java.io.IOException;

 import javax.servlet.ServletException;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;

 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.security.core.AuthenticationException;
 import org.springframework.security.web.AuthenticationEntryPoint;
 import org.springframework.stereotype.Component;

 @Component
 public class MyUnauthorizedEntryPoint implements AuthenticationEntryPoint {
     private final Logger logger = LoggerFactory.getLogger(MyUnauthorizedEntryPoint.class);

     @Override
     public void commence(HttpServletRequest request, HttpServletResponse response,
             AuthenticationException authException) throws IOException, ServletException {

         logger.debug("Rejecting access");
         response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized Entry");
     }
 }