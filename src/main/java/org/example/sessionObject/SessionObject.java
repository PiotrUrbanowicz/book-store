package org.example.sessionObject;

import org.example.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Optional;

@Component
@SessionScope
public class SessionObject {
   private User user=null;
    private Optional<String> pattern = Optional.empty();
    private String info=null;
   public boolean isLogged(){
       return this.user!=null;
   }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Optional<String> getPattern() {
        Optional<String> temp=this.pattern;
        this.pattern=Optional.empty();
       setPattern("");
        return temp;
    }
    public void setPattern(String pattern) {
        this.pattern = Optional.of(pattern);
    }
    public void setInfo(String info) {
        this.info = info;
    }

    public Optional<String> getInfo() {
        Optional<String> result = Optional.empty();
        if(this.info != null) {
            result = Optional.of(this.info);
        }
        this.info = null;
        return result;
    }

    public boolean isAdmin(){
       return (this.user!=null && this.user.getRole()== User.Role.ADMIN);
    }



}

