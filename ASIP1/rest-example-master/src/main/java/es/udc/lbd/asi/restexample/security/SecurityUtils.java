package es.udc.lbd.asi.restexample.security;

 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.security.core.Authentication;
 import org.springframework.security.core.context.SecurityContext;
 import org.springframework.security.core.context.SecurityContextHolder;
 import org.springframework.security.core.userdetails.UserDetails;

 public class SecurityUtils {
     private static final Logger logger = LoggerFactory.getLogger(SecurityUtils.class);

     private SecurityUtils() {
     }

     public static String getCurrentUserLogin() {
         SecurityContext securityContext = SecurityContextHolder.getContext();
         Authentication authentication = securityContext.getAuthentication();
         if (authentication != null) {
             if (authentication.getPrincipal() instanceof UserDetails) {
                 UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
                 return springSecurityUser.getUsername();
             } else {
                 logger.error("Authentication is not UserDetails! but {}", authentication.getClass().toString());
             }
         }
         return null;
     }
 }