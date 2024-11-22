package com.ead.authuser.controllers;

import com.ead.authuser.dtos.UserDto;
import com.ead.authuser.enums.UserStatus;
import com.ead.authuser.enums.UserType;
import com.ead.authuser.models.UserModel;
import com.ead.authuser.services.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
public class AuthenticationController {

//    The "5 Ws" of logging
//    Who: Identifies the user or system component that triggered the event.
//    What: Describes the action or event that occurred.
//    When: Records the timestamp of when the event occurred.
//    Where: Indicates the location within the system where the event took place.
//    Why: Explains the reason behind the event or action.

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(
            @RequestBody @Validated(UserDto.UserView.RegistrationPost.class)
            @JsonView(UserDto.UserView.RegistrationPost.class) UserDto userDto
    ) {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC"));
        log.info("{}, Acao=AuthenticationController.registerUser, Tentando registrar usuário. userDto: {}", now, userDto.toString()); // Who, What, When, Where

        if (userService.existsByUsername(userDto.getUsername())) {
            log.warn("{}, Acao=AuthenticationController.registerUser, Username já está em uso. Username: {}", now, userDto.getUsername()); // Why
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Username is already taken!");
        }

        if (userService.existsByEmail(userDto.getEmail())) {
            log.warn("{}, Acao=AuthenticationController.registerUser, Email já está em uso. Email: {}", now, userDto.getEmail()); // Why
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Email is already in use!");
        }

        var userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
        userModel.setUserStatus(UserStatus.ACTIVE);
        userModel.setUserType(UserType.STUDENT);
        userModel.setCreationDate(now);
        userModel.setLastUpdateDate(now);

        userService.save(userModel);

        log.info("{}, Acao=AuthenticationController.registerUser, Usuário registrado com sucesso. userDto: {}", now, userDto.toString()); // Who, What, When, Where

        return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
    }

    @GetMapping("/")
    public String index() {
        log.trace("TRACE");
        log.debug("DEBUG");
        log.info("INFO");
        log.warn("WARN");
        log.error("ERROR");
        return "Welcome to the EAD platform!";
    }

}
