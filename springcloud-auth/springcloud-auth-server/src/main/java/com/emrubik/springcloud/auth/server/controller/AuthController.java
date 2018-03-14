package com.emrubik.springcloud.auth.server.controller;

import com.emrubik.springcloud.auth.server.service.AuthService;
import com.emrubik.springcloud.domain.jwt.JwtAuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

//    @RequestMapping(value = "token", method = RequestMethod.POST)
//    public ResponseEntity<?> createToken(
//            @RequestBody JwtAuthenticationRequest authenticationRequest) throws Exception {
//        final String token = authService.createToken(authenticationRequest.getUsername(), authenticationRequest.getPassword());
//        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
//    }

    @RequestMapping(value = "token", method = RequestMethod.GET)
    public ResponseEntity<?> createToken() throws Exception {
        String token = authService.createToken("zhangsan", "123");
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }
}
