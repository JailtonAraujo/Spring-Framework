package com.br.final_jwt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class demoController {

    @GetMapping("/")
    public ResponseEntity<String> testapi (){

        return ResponseEntity.ok("API is Running...");

    }
}
