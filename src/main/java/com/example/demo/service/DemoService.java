package com.example.demo.service;

import com.example.demo.request.DemoRequest;
import com.example.demo.response.DemoResponse;

public interface DemoService{
    
    DemoResponse demoFunction(DemoRequest body);

}
