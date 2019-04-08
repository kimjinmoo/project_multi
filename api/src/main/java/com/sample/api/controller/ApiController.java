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

    /**
     *
     * Sample Add
     *
     * @return void
     */
    @PostMapping("/sample")
    public ResponseEntity<Void> saveSample() {

        Sample sample = new Sample();
        sample.setAge(1);
        sample.setName("3tier_complete");
        commonService.saveSample(sample);

        return new ResponseEntity<Void>( HttpStatus.OK);
    }

    /**
     *
     * Sample All List
     *
     * @return void
     */
    @GetMapping("/sample")
    public Object findSampleAll() {
        return commonService.findSampleAll();
    }

    /**
     *
     * Sample ID 조회
     *
     * @return void
     */
    @GetMapping("/sample/{id}")
    public Object findSampleById(@PathVariable String id) {
        return commonService.findSampleById(id);
    }

    /**
     *
     * Sample Update
     *
     * @return void
     */
    @PutMapping("/sample/{id}")
    public ResponseEntity<Void> updateSample(@PathVariable String id, Sample sample) {
        // set ID
        sample.setId(id);
        commonService.updateSample(sample);

        return new ResponseEntity<Void>( HttpStatus.OK);
    }

    /**
     *
     * Sample delete
     *
     * @return void
     */
    @DeleteMapping("/sample/{id}")
    public ResponseEntity<Void> deleteSample(@PathVariable String id, Sample sample) {
        // delete
        commonService.deleteSample(id);
        return new ResponseEntity<Void>( HttpStatus.OK);
    }

    /**
     *
     * MySql 페이징 처리 조회
     *
     * @return User
     */
    @GetMapping("/rdb/user")
    public List<User> getUser(@RequestParam int page) {
        return commonService.findUserAll(page);
    }

    /**
     *
     * MySql ID로 조회
     *
     * @return User
     */
    @GetMapping("/rdb/user/{id}")
    public User getUserById(@PathVariable int id) {
        return commonService.findUserById(id);
    }

    /**
     *
     * Mysql User 등록
     *
     * @param user
     * @return User
     */
    @PostMapping("/rdb/user")
    public User saveUser(@RequestBody User user) {
        commonService.saveUsers(user);

        return commonService.findUserById(user.getId());
    }

    /**
     *
     * Mysql User 수정
     *
     * @param user User
     * @return User
     */
    @PutMapping("/rdb/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user) {
        // id를 Set
        user.setId(id);
        commonService.updateUser(user);

        return commonService.findUserById(user.getId());
    }

    /**
     *
     * Mysql User 삭제
     *
     * @param id id
     * @return void
     */
    @DeleteMapping("/rdb/user/{id}")
    public  void deleteUser(@PathVariable int id) {
        commonService.deleteUser(id);
    }
}
