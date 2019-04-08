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
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/sample/web")
    public ModelAndView web(@RequestParam int page) {
        ModelAndView mv = new ModelAndView();

        RestTemplate restTemplate = new RestTemplate();

        String resourceUrl = "http://localhost:8180/api/rdb/user?page="+page;
        String response = restTemplate.getForObject(resourceUrl, String.class);

        SampleListResponse list = new Gson().fromJson(response, new TypeToken<SampleListResponse>(){}.getType());

        mv.addObject("data", list);
        mv.setViewName("/sample/web/index");

        return mv;
    }
}
