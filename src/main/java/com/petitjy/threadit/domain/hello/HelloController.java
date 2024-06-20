package com.petitjy.threadit.domain.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 테스트를 위한 임시 컨트롤러
 */
@RestController
@RequestMapping("/v1/hello")
public class HelloController {

    @GetMapping
    public String hello() {
        return "Hello Spring";
    }

    @GetMapping("/{name}")
    public String helloName(@PathVariable(name = "name") String name) {
        return "Hello " + name;
    }
}
