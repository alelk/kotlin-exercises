package com.github.alelk.template_kotlin_springboot_webflux_r2dbc.core.repository

import com.github.alelk.template_kotlin_springboot_webflux_r2dbc.core.entity.UserEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface UserRepository: CoroutineCrudRepository<UserEntity,Long>