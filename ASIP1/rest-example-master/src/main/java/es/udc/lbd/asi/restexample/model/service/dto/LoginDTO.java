package es.udc.lbd.asi.restexample.model.service.dto;

 import javax.validation.constraints.NotEmpty;
 import javax.validation.constraints.Size;

 public class LoginDTO {
     @NotEmpty
     private String login;
     @NotEmpty
     @Size(min = 4)
     private String password;

     public String getLogin() {
         return login;
     }

     public void setLogin(String login) {
         this.login = login;
     }

     public String getPassword() {
         return password;
     }

     public void setPassword(String password) {
         this.password = password;
     }
 }