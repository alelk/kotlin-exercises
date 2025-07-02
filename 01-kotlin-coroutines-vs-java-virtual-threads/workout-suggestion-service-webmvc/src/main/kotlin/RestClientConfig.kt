package com.github.alelk.kotlin_exercises.excercise01.workout_suggestion_service.kotlin_coroutines

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient
import org.apache.hc.client5.http.impl.classic.HttpClients
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestTemplate

@Configuration
class RestClientConfig {

  @Bean
  fun restTemplate(): RestTemplate {
    val connectionManager = PoolingHttpClientConnectionManager().apply {
      maxTotal = 50
      defaultMaxPerRoute = 50
    }
    val httpClient: CloseableHttpClient =
      HttpClients.custom()
        .setConnectionManager(connectionManager)
        .build()
    val requestFactory = HttpComponentsClientHttpRequestFactory(httpClient)
    return RestTemplate(requestFactory)
  }
}