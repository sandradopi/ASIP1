package es.udc.lbd.asi.restexample.security;

 import java.io.IOException;

 import javax.servlet.ServletException;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;

 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.security.access.AccessDeniedException;
 import org.springframework.security.web.access.AccessDeniedHandler;
 import org.springframework.stereotype.Component;

 @Component
 public class MyAccessDeniedHandler implements AccessDeniedHandler {
     private final Logger logger = LoggerFactory.getLogger(MyAccessDeniedHandler.class);

     @Override
     public void handle(HttpServletRequest request, HttpServletResponse response,
             AccessDeniedException accessDeniedException) throws IOException, ServletException {

         logger.debug("Access denied! Username or password incorrect");
         response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access denied! Username or password incorrect");
     }
 }