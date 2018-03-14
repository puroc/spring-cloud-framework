package com.emrubik.springcloud.auth.server.controller;

import com.emrubik.springcloud.auth.server.service.AuthService;
import com.emrubik.springcloud.domain.jwt.JwtAuthenticationResponse;
import com.emrubik.springcloud.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("token")
    public ResponseEntity<?> createToken(User user) throws Exception {
        String token = authService.createToken(user);
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }
}
