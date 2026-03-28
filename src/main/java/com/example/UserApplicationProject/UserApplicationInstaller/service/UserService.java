package com.example.UserApplicationProject.UserApplicationInstaller.service;


import com.example.UserApplicationProject.UserApplicationInstaller.dto.UserRequestDto;
import com.example.UserApplicationProject.UserApplicationInstaller.dto.UserResponseDto;
import com.example.UserApplicationProject.UserApplicationInstaller.dto.UserUpdateRequestDto;
import com.example.UserApplicationProject.UserApplicationInstaller.entity.User;
import com.example.UserApplicationProject.UserApplicationInstaller.exception.UserNotFoundException;
import com.example.UserApplicationProject.UserApplicationInstaller.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @CacheEvict(value = "users", allEntries = true)
    public UserResponseDto registerUser(@Valid @RequestBody UserRequestDto dto) {

        // Check email uniqueness
        userRepository.findByEmail(dto.getEmail())
                .ifPresent(u -> {
                    throw new RuntimeException("Email already exists");
                });

        // Map DTO → Entity
        User user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .build();

        // Save to DB
        User savedUser = userRepository.save(user);

        // Map Entity → Response DTO
        return UserResponseDto.builder()
                .id(savedUser.getId())
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .phone(savedUser.getPhone())
                .build();
    }

    @Cacheable(value = "users",key = "#id")
    public UserResponseDto getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .build();
    }

    @CacheEvict(value = "users", key = "#id")
    public UserResponseDto updateUser(Long id, UserUpdateRequestDto dto) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(dto.getName());
        user.setPhone(dto.getPhone());

        User updatedUser = userRepository.save(user);

        return UserResponseDto.builder()
                .id(updatedUser.getId())
                .name(updatedUser.getName())
                .email(updatedUser.getEmail())
                .phone(updatedUser.getPhone())
                .build();
    }

    @CacheEvict(value = "users", key = "#id")
    public void deleteUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userRepository.delete(user);
    }

}
