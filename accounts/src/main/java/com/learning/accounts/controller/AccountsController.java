package com.learning.accounts.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AccountsController {

    @GetMapping("/mission")
    public Map<String, String> helloRoute(){
        Map<String, String> map = new HashMap<>();
        map.put("name","Mission 50");

        return map;
    }

}
