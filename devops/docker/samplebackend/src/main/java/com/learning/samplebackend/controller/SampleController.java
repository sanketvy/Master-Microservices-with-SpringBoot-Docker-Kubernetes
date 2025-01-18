package com.learning.samplebackend.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class SampleController {

    @GetMapping("/name")
    public Map<String, String> getName(){
        Map<String, String> map = new HashMap<>();
        map.put("name", "Sanket Vyawahare from Docker");
        return map;
    } 
}
