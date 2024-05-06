package com.github.alelk.kotlin_exercises.excercise01.workout_suggestion_service.webmvc

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient

@Configuration
class RestClientConfig {

  @Bean
  fun restClient() = RestClient.create("http://127.0.0.1:8080/")
}