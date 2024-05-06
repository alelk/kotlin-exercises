package com.github.alelk.kotlin_exercises.excercise01.workout_suggestion_service.kotlin_coroutines

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import reactor.netty.resources.ConnectionProvider

@Configuration
class WebClientConfig {

  @Bean
  fun webClient() =
    WebClient.builder()
      .baseUrl("http://127.0.0.1:8080/")
      .clientConnector(
        ReactorClientHttpConnector(
          HttpClient.create(
            ConnectionProvider
              .builder("user-activity-svc-client")
              .pendingAcquireMaxCount(2000)
              .maxConnections(2000).build()
          )
        )
      )
      .build()
}