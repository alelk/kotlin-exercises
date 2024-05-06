package com.github.alelk.template_kotlin_springboot_webflux_r2dbc.app.controller

import com.github.alelk.template_kotlin_springboot_webflux_r2dbc.app.dto.UserDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController("/users")
class UsersController {

  @GetMapping
  fun getUsers(): List<UserDto> = TODO()
}