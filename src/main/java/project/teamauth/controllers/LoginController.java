package project.teamauth.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import project.teamauth.configuration.jwt.JwtTokenUtil;
import project.teamauth.dtos.LoginRequestDto;
import project.teamauth.dtos.LoginResponseDto;

import javax.validation.Valid;

@CrossOrigin()
@RestController
@RequestMapping("/v1")
@AllArgsConstructor
public class LoginController {

    private final AuthenticationManager authManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto dto) throws Exception {
        try {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getLogin(), dto.getPassword()));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(dto.getLogin());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new LoginResponseDto(token));
    }
}