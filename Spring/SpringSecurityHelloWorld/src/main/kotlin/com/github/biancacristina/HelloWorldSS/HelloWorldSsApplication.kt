package com.github.biancacristina.HelloWorldSS

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HelloWorldSsApplication

fun main(args: Array<String>) {
	runApplication<HelloWorldSsApplication>(*args)
}
