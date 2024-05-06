package com.github.alelk.kotlin_exercises.excercise01.workout_suggestion_service.kotlin_coroutines

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.jackson.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class HttpClientConfig {

  @Bean
  fun httpClient() = HttpClient(CIO) {
    install(ContentNegotiation) { jackson() }
    defaultRequest { url("http://127.0.0.1:8080/") }
    engine {
      maxConnectionsCount = 2000
      endpoint {
        connectTimeout = 30_000
        requestTimeout = 30_000
        maxConnectionsCount = 2000
        keepAliveTime = 20_000
        maxConnectionsPerRoute = 1000
        pipelineMaxSize = 20
      }
    }
  }

}