package com.sample.hello.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @GetMapping("/test")
  public ResponseEntity<String> test() {
    return new ResponseEntity<>("Hello", HttpStatus.OK);
  }
}
