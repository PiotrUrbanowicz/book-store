package org.example.controllers;

import jakarta.annotation.Resource;
import org.apache.commons.codec.digest.DigestUtils;
import org.example.database.IUserDAO;
import org.example.exceptions.UserLoginExistException;
import org.example.exceptions.UserValidationException;
import org.example.model.User;
import org.example.services.IAuthenticationService;
import org.example.sessionObject.SessionObject;
import org.example.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class AuthenticationController {

@Autowired
IAuthenticationService authenticationServiceImpl;
@Resource
SessionObject sessionObject;

@RequestMapping(path="/login", method = RequestMethod.GET)
public String login(Model model){
    model.addAttribute("logged",this.sessionObject.isLogged());
    return "login";
}


    @RequestMapping(path="/login", method = RequestMethod.POST)
    public String login(@RequestParam String login, @RequestParam String password){
    try{
        UserValidator.validateLogin(login);
        UserValidator.validatePassword(password);
        if(!this.authenticationServiceImpl.authenticate(login,password)){
            return "redirect:/login";
        }
    }catch (UserValidationException e){
        e.printStackTrace();
        return "redirect:/login";
    }
        return "redirect:/main";
    }

@RequestMapping(path = "/register", method = RequestMethod.GET)
    public String register(Model model){
    model.addAttribute("logged",this.sessionObject.isLogged());
    model.addAttribute("user",new User());
    return "register";
}
    @RequestMapping(path = "/register", method = RequestMethod.POST)//
    public String register(@ModelAttribute User user, @RequestParam String password2){
        try {
            UserValidator.validateRegisterUser(user, password2);
            this.authenticationServiceImpl.registerUser(user);
        } catch (UserValidationException | UserLoginExistException e) {
            e.printStackTrace();
            return "redirect:/register";
        }
        return "redirect:/login";
    }

    @RequestMapping(path="/logout", method=RequestMethod.GET)
    public String logout(Model model){
    this.authenticationServiceImpl.logout();
    return "redirect:/login";
    }

}
