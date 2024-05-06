package com.github.alelk.kotlin_exercises.excercise01.workout_suggestion_service.webmvc

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient

@Configuration
class RestClientConfig {

  @Bean
  fun restTemplate() = RestTemplateBuilder().build()

  @Bean
  fun restClient() = RestClient
    .builder()
    .baseUrl("http://127.0.0.1:8080/")
    .build()
}