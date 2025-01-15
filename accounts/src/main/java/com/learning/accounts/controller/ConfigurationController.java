package com.learning.accounts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/config")
@RefreshScope
public class ConfigurationController {

    @Value("${root.name}")
    private String name;

    @Value("${root.game}")
    private String game;

    @Autowired
    private Environment environment;

    @GetMapping("/env")
    public String getEnvValue() {
        return environment.getProperty("M2_HOME", "Not Found");
    }

    @GetMapping("/prop")
    public String getPropValue() {
        return name;
    }

    @GetMapping("/prop/game")
    public String getPropGameValue() {
        return game;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }
}
