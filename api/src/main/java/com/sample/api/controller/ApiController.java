package com.sample.api.controller;

import com.sample.common.domain.Sample;
import com.sample.common.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ApiController {

    @Autowired
    private CommonService commonService;

    @GetMapping("/sample/any")
    public ResponseEntity<String> findAny() {
        log.info("any : {}" , "call findAny");
        return new ResponseEntity<String>(commonService.findAll().stream().findAny().get().getName(), HttpStatus.OK);
    }

    @PostMapping("/sample")
    public ResponseEntity<Void> add() {

        Sample sample = new Sample();
        sample.setAge(1);
        sample.setName("3tier_complete");
        commonService.create(sample);

        return new ResponseEntity<Void>( HttpStatus.OK);
    }

}
