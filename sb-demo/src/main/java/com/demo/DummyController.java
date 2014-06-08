package com.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyController {
	
	@Autowired
	DummyBean dummyBean;
	
    @RequestMapping("/dummy")
    public String index() {
        return "This is the dummy controller - " + dummyBean.getMessage();
    }

}