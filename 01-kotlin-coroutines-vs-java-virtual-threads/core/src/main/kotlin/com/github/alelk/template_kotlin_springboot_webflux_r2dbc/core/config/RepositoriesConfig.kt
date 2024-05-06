package com.github.alelk.template_kotlin_springboot_webflux_r2dbc.core.config

import com.github.alelk.template_kotlin_springboot_webflux_r2dbc.core.entity.UserEntity
import com.github.alelk.template_kotlin_springboot_webflux_r2dbc.core.repository.UserRepository
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@Configuration
@EnableR2dbcRepositories(
  basePackageClasses = [
    UserRepository::class,
    UserEntity::class
  ]
)
class RepositoriesConfig {
}