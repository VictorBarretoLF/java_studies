package com.ead.authuser.controllers;

import com.ead.authuser.dtos.UserDto;
import com.ead.authuser.models.UserModel;
import com.ead.authuser.services.UserService;
import com.ead.authuser.specifications.SpecificationTemplate;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<Page<UserModel>> getAllUsers(
            SpecificationTemplate.UserSpec spec,
            @PageableDefault(page = 0, size = 10, sort = "userID", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC"));
        log.info("{}, Acao=UserController.getAllUsers, Tentando buscar todos os usuários.", now); // Who, What, When, Where

        Page<UserModel> userModelPage = userService.findAll(pageable, spec);

        if(!userModelPage.isEmpty()) {
            userModelPage.forEach(user -> user.add(
                    linkTo(methodOn(UserController.class).getUserById(user.getUserID())).withSelfRel()
            ));
        }

        log.info("{}, Acao=UserController.getAllUsers, Usuários buscados com sucesso.", now); // Who, What, When, Where

        return ResponseEntity.status(HttpStatus.OK).body(userModelPage);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable(value = "userId") UUID userId) {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC"));
        log.info("{}, Acao=UserController.getUserById, Tentando buscar usuário. userId: {}", now, userId); // Who, What, When, Where

        Optional<UserModel> userModelOptional = userService.findById(userId);

        if(!userModelOptional.isPresent()) {
            log.warn("{}, Acao=UserController.getUserById, Usuário não encontrado. userId: {}", now, userId); // Why
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        log.info("{}, Acao=UserController.getUserById, Usuário encontrado com sucesso. userId: {}", now, userId); // Who, What, When, Where

        return ResponseEntity.status(HttpStatus.OK).body(userModelOptional.get());
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUserById(@PathVariable(value = "userId") UUID userId) {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC"));
        log.info("{}, Acao=UserController.deleteUserById, Tentando deletar usuário. userId: {}", now, userId); // Who, What, When, Where

        Optional<UserModel> userModelOptional = userService.findById(userId);

        if(!userModelOptional.isPresent()) {
            log.warn("{}, Acao=UserController.deleteUserById, Usuário não encontrado. userId: {}", now, userId); // Why
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        userService.deleteById(userId);

        log.info("{}, Acao=UserController.deleteUserById, Usuário deletado com sucesso. userId: {}", now, userId); // Who, What, When, Where

        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Object> updateUser(
            @PathVariable(value = "userId") UUID userId,
            @RequestBody @Validated(UserDto.UserView.UserPut.class)
            @JsonView(UserDto.UserView.UserPut.class) UserDto userDto
    ) {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC"));
        log.info("{}, Acao=UserController.updateUser, Tentando atualizar usuário. userId: {}", now, userId); // Who, What, When, Where

        Optional<UserModel> userModelOptional = userService.findById(userId);

        if(!userModelOptional.isPresent()) {
            log.warn("{}, Acao=UserController.updateUser, Usuário não encontrado. userId: {}", now, userId); // Why
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        var userModel = userModelOptional.get();
        userModel.setFullName(userDto.getFullName());
        userModel.setPhoneNumber(userDto.getPhoneNumber());
        userModel.setCpf(userDto.getCpf());
        userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));

        userService.save(userModel);

        log.info("{}, Acao=UserController.updateUser, Usuário atualizado com sucesso. userId: {}", now, userId); // Who, What, When, Where

        return ResponseEntity.status(HttpStatus.OK).body(userModel);
    }

    @PutMapping("/{userId}/password")
    public ResponseEntity<Object> updatePassword(
            @PathVariable(value = "userId") UUID userId,
            @RequestBody @Validated(UserDto.UserView.PasswordPut.class)
            @JsonView(UserDto.UserView.PasswordPut.class)UserDto userDto
    ) {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC"));
        log.info("{}, Acao=UserController.updatePassword, Tentando atualizar senha do usuário. userId: {}", now, userId); // Who, What, When, Where

        Optional<UserModel> userModelOptional = userService.findById(userId);

        if(!userModelOptional.isPresent()) {
            log.warn("{}, Acao=UserController.updatePassword, Usuário não encontrado. userId: {}", now, userId); // Why
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        if(!userModelOptional.get().getPassword().equals(userDto.getOldPassword())) {
            log.warn("{}, Acao=UserController.updatePassword, Senha antiga não confere. userId: {}", now, userId); // Why
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Mismatched old password!");
        }

        var userModel = userModelOptional.get();
        userModel.setPassword(userDto.getPassword());
        userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));

        userService.save(userModel);

        log.info("{}, Acao=UserController.updatePassword, Senha do usuário atualizada com sucesso. userId: {}", now, userId); // Who, What, When, Where

        return ResponseEntity.status(HttpStatus.OK).body("Password updated successfully!");
    }

    @PutMapping("/{userId}/image")
    public ResponseEntity<Object> updateImage(
            @PathVariable(value = "userId") UUID userId,
            @RequestBody @Validated(UserDto.UserView.RegistrationPost.class)
            @JsonView(UserDto.UserView.ImagePut.class) UserDto userDto
    ) {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC"));
        log.info("{}, Acao=UserController.updateImage, Tentando atualizar imagem do usuário. userId: {}", now, userId); // Who, What, When, Where

        Optional<UserModel> userModelOptional = userService.findById(userId);

        if(!userModelOptional.isPresent()) {
            log.warn("{}, Acao=UserController.updateImage, Usuário não encontrado. userId: {}", now, userId); // Why
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        var userModel = userModelOptional.get();
        userModel.setImageUrl(userDto.getImageUrl());
        userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));

        userService.save(userModel);

        log.info("{}, Acao=UserController.updateImage, Imagem do usuário atualizada com sucesso. userId: {}", now, userId); // Who, What, When, Where

        return ResponseEntity.status(HttpStatus.OK).body(userModel);
    }

}
