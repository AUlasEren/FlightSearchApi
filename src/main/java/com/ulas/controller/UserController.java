package com.ulas.controller;

import com.ulas.dto.request.*;
import com.ulas.repository.entity.Flights;
import com.ulas.repository.entity.User;
import com.ulas.service.FlightsService;
import com.ulas.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ulas.constants.EndPoints.*;
import static com.ulas.constants.EndPoints.GET_ALL;

@RestController
@RequiredArgsConstructor
@RequestMapping(USER)
public class UserController {
    private final UserService userService;

    @PostMapping(CREATE)
    public ResponseEntity<User> createUser(@RequestBody CreateUserRequestDto dto) {
        return ResponseEntity.ok(userService.createUser(dto));

    }

    @PutMapping(UPDATE)
    public ResponseEntity<User> updateUser(@RequestBody UpdateUserRequestDto dto) {
        return ResponseEntity.ok(userService.updateUser(dto));
    }

    @DeleteMapping(DELETE)
    public ResponseEntity<User> deleteUser(Long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }


    @GetMapping(GET_ALL)
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping(DO_LOGIN)
    public ResponseEntity<String> login(@RequestBody LoginUserRequestDto dto) {

        return ResponseEntity.ok(userService.login(dto));
    }
    @PostMapping(VALIDATE_TOKEN)
    public ResponseEntity<?> validateToken(String token){
        return ResponseEntity.ok(userService.validateToken(token));
    }
}
