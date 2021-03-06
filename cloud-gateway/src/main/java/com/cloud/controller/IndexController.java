package com.cloud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * Created by sunhaidi on 2019-03-05.
 */
@RestController
@RequestMapping(value = "/api")
public class IndexController {

//    @Autowired
//    CustomerService customerService;

    @RequestMapping(path = "/customer", method = RequestMethod.GET)
    Mono<String> index() {
        System.out.println("customer");
        return Mono.just("customer");
    }

    @RequestMapping(path = "/index2", method = RequestMethod.GET)
    public String index2() {
        System.out.println("index2");
       return "index2";
    }

}
