package com.sample.api.controller;

import com.sample.common.entity.Sample;
import com.sample.common.entity.User;
import com.sample.common.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ApiController {

    @Autowired
    private CommonService commonService;

    @GetMapping("/sample/any")
    public ResponseEntity<String> findAny() {
        log.info("any : {}" , "call findAny");
        return new ResponseEntity<String>(commonService.findSampleAll().stream().findAny().get().getName(), HttpStatus.OK);
    }

    @PostMapping("/sample")
    public ResponseEntity<Void> add() {

        Sample sample = new Sample();
        sample.setAge(1);
        sample.setName("3tier_complete");
        commonService.createSample(sample);

        return new ResponseEntity<Void>( HttpStatus.OK);
    }

    @GetMapping("/rdb/user")
    public Object getUser(@RequestParam int page) {
        return commonService.findUserAll(page);
    }

    @GetMapping("/rdb/user/{id}")
    public User getUserById(@PathVariable int id) {
        return commonService.findUserById(id);
    }

    @PostMapping("/rdb/user")
    public User saveUser(@RequestBody User user) {
        commonService.saveUsers(user);

        return commonService.findUserById(user.getId());
    }

    @PutMapping("/rdb/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user) {
        // id를 Set
        user.setId(id);
        commonService.updateUser(user);

        return commonService.findUserById(user.getId());
    }

    @DeleteMapping("/rdb/user/{id}")
    public  void deleteUser(@PathVariable int id) {
        commonService.deleteUser(id);
    }
}
