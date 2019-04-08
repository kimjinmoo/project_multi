package com.sample.front.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sample.front.domain.SampleListResponse;
import com.sample.front.domain.User;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
public class FrontController {

    @GetMapping("/")
    public ModelAndView root() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/index");
        return mv;
    }

    @GetMapping("/sample/api")
    public ModelAndView api() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/sample/api/list");

        return mv;
    }

    @GetMapping("/sample/web/list")
    public ModelAndView webList(@RequestParam(required = false) int page) {
        ModelAndView mv = new ModelAndView();

        RestTemplate restTemplate = new RestTemplate();

        String resourceUrl = "http://localhost:8180/api/rdb/user?page="+page;
        String response = restTemplate.getForObject(resourceUrl, String.class);

        SampleListResponse list = new Gson().fromJson(response, new TypeToken<SampleListResponse>(){}.getType());

        mv.addObject("data", list);
        mv.setViewName("/sample/web/list");

        return mv;
    }

    @GetMapping("/sample/web/detail")
    public ModelAndView webDetail(@RequestParam int id) {
        ModelAndView mv = new ModelAndView();

        RestTemplate restTemplate = new RestTemplate();

        String resourceUrl = "http://localhost:8180/api/rdb/user/"+id;
        String response = restTemplate.getForObject(resourceUrl, String.class);

        User user = new Gson().fromJson(response, new TypeToken<User>(){}.getType());

        mv.addObject("data", user);
        mv.setViewName("/sample/web/detail");

        return mv;
    }

    @GetMapping("/sample/web/add")
    public ModelAndView webAdd() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/sample/web/add");
        return mv;
    }

    @PostMapping("/sample/web/add")
    public ModelAndView webAdd(@ModelAttribute User user) {
        ModelAndView mv = new ModelAndView();

        RestTemplate restTemplate = new RestTemplate();

        String resourceUrl = "http://localhost:8180/api/rdb/user";

        // set Header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Set Params
        HttpEntity<String> entity = new HttpEntity<String>(new Gson().toJson(user), headers);

        // send Api
        restTemplate.postForObject(resourceUrl, entity, String.class);

        mv.setViewName("redirect:/sample/web/list?page=1");

        return mv;
    }

    @PostMapping("/sample/web/update")
    public ModelAndView webUpdate(@ModelAttribute User user) {
        ModelAndView mv = new ModelAndView();

        RestTemplate restTemplate = new RestTemplate();


        String resourceUrl = "http://localhost:8180/api/rdb/user/"+user.getId();
        // set Header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Set Params
        HttpEntity<String> entity = new HttpEntity<String>(new Gson().toJson(user), headers);

        // send Api
        restTemplate.put(resourceUrl, entity, String.class);

        mv.setViewName("redirect:/sample/web/list?page=1");

        return mv;
    }

    @PostMapping("/sample/web/delete")
    public ModelAndView webDelete(@RequestParam int id) {
        ModelAndView mv = new ModelAndView();

        RestTemplate restTemplate = new RestTemplate();

        String resourceUrl = "http://localhost:8180/api/rdb/user/"+id;
        restTemplate.delete(resourceUrl);

        mv.setViewName("redirect:/sample/web/list?page=1");

        return mv;
    }
}
