package com.boot.controller;

import com.boot.bean.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloworldController {
    @RequestMapping("/hello")
    public String helloworld() {
        return "Hello World";
    }

    @Autowired
    Car car;

    @RequestMapping("/car")
    public Car car() {
        return car;
    }
}
