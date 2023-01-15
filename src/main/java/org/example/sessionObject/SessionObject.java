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
}
