package tech.employeeLeaveManagementSystem.employee_leave_management_system.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.employeeLeaveManagementSystem.employee_leave_management_system.config.JwtUtils;
import tech.employeeLeaveManagementSystem.employee_leave_management_system.dto.AuthResponseDto;
import tech.employeeLeaveManagementSystem.employee_leave_management_system.dto.LoginDto;
import tech.employeeLeaveManagementSystem.employee_leave_management_system.service.UserService;

import java.util.List;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final UserService userService;
    private final JwtUtils jwtUtil;

    public AuthenticationController(UserService userService, JwtUtils jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto) {

        String username = loginDto.getUsername();
        String rawPassword = loginDto.getPassword();

        // Fetch user from hardcoded user map
        UserService.TestUser user = userService.getUsers().get(username);

        if (user == null) {
            return ResponseEntity.status(401).body(
                    new AuthResponseDto(null, "Bearer", null, List.of("Invalid username"))
            );
        }

        // Validate password
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(rawPassword, user.password())) {
            return ResponseEntity.status(401).body(
                    new AuthResponseDto(null, "Bearer", null, List.of("Invalid password"))
            );
        }

        // Generate JWT
        String token = jwtUtil.generateToken(user.id(), user.role());

        // Build response
        AuthResponseDto response = new AuthResponseDto(
                token,                           // accessToken
                "Bearer",                        // tokenType
                user.id().toString(),            // userId
                List.of(user.role())             // roles
        );

        return ResponseEntity.ok(response);
    }


}
