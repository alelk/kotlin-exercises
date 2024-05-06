package com.github.alelk.template_kotlin_springboot_webflux_r2dbc.app.dto

data class UserDto(
  val id: Long?,
  val name: String,
  val email: String,
  val phone: String
)
