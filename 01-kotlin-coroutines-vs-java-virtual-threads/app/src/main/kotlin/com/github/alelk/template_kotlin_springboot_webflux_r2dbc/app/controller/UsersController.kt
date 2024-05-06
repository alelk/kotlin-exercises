package com.github.alelk.template_kotlin_springboot_webflux_r2dbc.app.controller

import com.github.alelk.template_kotlin_springboot_webflux_r2dbc.app.dto.UserDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
@Tag(name = "/users", description = "Users dictionary")
class UsersController {

  @Operation(description = "Get all users")
  @GetMapping
  fun getUsers(): List<UserDto> = TODO()
}