package com.manish.tls.demo.controller;

import com.manish.tls.demo.service.DevtoolTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    DevtoolTest devtoolTest;
    @GetMapping("/welcome")
    public ResponseEntity<String> welcome(){

        return ResponseEntity.ok("Welcome to tls demo..");
    }

    @GetMapping("/welcome2")
    public ResponseEntity<String> welcome2(){

        return ResponseEntity.ok("this is the second msg..."+devtoolTest.testService());
    }
}
