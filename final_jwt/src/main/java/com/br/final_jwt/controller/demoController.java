package com.br.final_jwt.controller;

import com.br.final_jwt.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class demoController {

    @GetMapping("/")
    public ResponseEntity<String> testapi (@AuthenticationPrincipal User user){

        System.out.println("");

        return ResponseEntity.ok("API is Running...");

    }
}
