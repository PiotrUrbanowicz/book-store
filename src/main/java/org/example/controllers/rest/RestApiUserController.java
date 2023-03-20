package org.example.controllers.rest;

import jdk.jshell.spi.ExecutionControl;
import org.example.exceptions.UserLoginExistException;
import org.example.exceptions.UserNotExistException;
import org.example.model.User;
import org.example.model.dto.UserDTO;
import org.example.model.dto.UserResponseDTO;
import org.example.services.IUserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path="api/v1/user")
public class RestApiUserController {

    @Autowired
    IUserService userService;

    @RequestMapping(path="", method= RequestMethod.GET)
    public ResponseEntity<UserResponseDTO> getUserByLogin(@RequestParam String login){
        Optional<User> userBox=this.userService.getUserByLogin(login);

        return userBox
                .map(user->ResponseEntity.status(HttpStatus.OK).body(new UserResponseDTO(user)))
                .orElseGet(()->ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @RequestMapping(path = "",method = RequestMethod.POST)
    public ResponseEntity<User> persistUser(@RequestBody UserDTO user){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.persistUser(user));
        }catch (UserLoginExistException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @RequestMapping(path = "{id}",method = RequestMethod.GET)
    ResponseEntity<UserResponseDTO> getUserById(@PathVariable int id) throws ExecutionControl.NotImplementedException {
        Optional<User> userBox=this.userService.getUserById(id);

            return userBox
                    .map(user -> ResponseEntity.status(HttpStatus.OK).body(new UserResponseDTO(user)))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @RequestMapping(path="{userId}",method = RequestMethod.PUT)
    ResponseEntity<User> updateUser(@PathVariable int userId,
                                    @RequestBody UserDTO userDTO){
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(this.userService.updateUser(userDTO, userId));
       }catch (UserNotExistException e){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }catch(ExecutionControl.NotImplementedException e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
        }
    }
}

