package com.sample.front.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
}
