
package com.github.alelk.kotlin_exercises.excercise01.user_activity_service

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.reactive.config.CorsRegistry
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.reactive.config.WebFluxConfigurer

@Configuration
class ApiConfig : WebFluxConfigurer {

  @ConfigurationProperties("rest-api.cors")
  fun corsConfig() = CorsConfiguration()

  override fun addCorsMappings(registry: CorsRegistry) {
    registry.addMapping("/**").apply {
      val cfg = corsConfig()
      cfg.allowedOrigins?.let { allowedOrigins(*it.toTypedArray()) }
      cfg.allowedOriginPatterns?.let { allowedOriginPatterns(*it.toTypedArray()) }
      cfg.allowedMethods?.let { allowedMethods(*it.toTypedArray()) }
      cfg.allowedHeaders?.let { allowedHeaders(*it.toTypedArray()) }
      cfg.exposedHeaders?.let { exposedHeaders(*it.toTypedArray()) }
      cfg.allowCredentials?.let { allowCredentials(it) }
      cfg.maxAge?.let { maxAge(it) }
    }
  }
}