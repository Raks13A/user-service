package com.example.UserApplicationProject.UserApplicationInstaller.controller;


import com.example.UserApplicationProject.UserApplicationInstaller.dto.UserRequestDto;
import com.example.UserApplicationProject.UserApplicationInstaller.dto.UserResponseDto;
import com.example.UserApplicationProject.UserApplicationInstaller.dto.UserUpdateRequestDto;
import com.example.UserApplicationProject.UserApplicationInstaller.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // Register User
    @PostMapping
    public ResponseEntity<UserResponseDto> registerUser(
            @Valid @RequestBody UserRequestDto dto) {

        return ResponseEntity.ok(userService.registerUser(dto));
    }

    // Get User by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long id) {

        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserUpdateRequestDto dto) {

        return ResponseEntity.ok(userService.updateUser(id, dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

}
