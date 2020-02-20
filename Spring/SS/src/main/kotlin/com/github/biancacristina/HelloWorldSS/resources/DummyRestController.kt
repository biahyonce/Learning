package com.github.biancacristina.HelloWorldSS.resources

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
open class DummyRestController {

    @GetMapping("/test")
    fun testSecurity():String {
        return "Hello World Spring Security"
    }
}