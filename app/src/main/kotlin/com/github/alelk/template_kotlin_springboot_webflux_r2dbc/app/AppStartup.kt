package com.github.alelk.template_kotlin_springboot_webflux_r2dbc.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AppStartup

fun main(args: Array<String>) {
	runApplication<AppStartup>(*args)
}
