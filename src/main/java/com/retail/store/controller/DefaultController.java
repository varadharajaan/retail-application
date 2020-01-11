package com.retail.store.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class DefaultController {

    @RequestMapping("/")
    public String firstPage() {
        return "Hello Retailstore application successfully deployed and started...";
    }


}
