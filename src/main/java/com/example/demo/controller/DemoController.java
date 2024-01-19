package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.request.DemoRequest;
import com.example.demo.response.DemoResponse;
import com.example.demo.service.DemoService;

import jakarta.servlet.http.HttpServletResponse;


@RestController
public class DemoController {
    
    @Autowired
    DemoService service;

    DemoResponse dResponse = new DemoResponse();



    @PostMapping("/number")
    public DemoResponse postMethodName(@RequestBody DemoRequest body, HttpServletResponse response) {

        return dResponse = service.demoFunction(body);
    }
    

}
