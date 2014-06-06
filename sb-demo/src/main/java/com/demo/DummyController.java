package com.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyController {

    @RequestMapping("/dummy")
    public String index() {
        return "This is the dummy controller";
    }

}