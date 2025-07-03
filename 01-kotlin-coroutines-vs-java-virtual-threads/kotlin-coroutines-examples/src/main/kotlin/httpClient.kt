package kotlin_coroutines_example

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.jackson.*

val httpClient = HttpClient(CIO) {
  install(ContentNegotiation) { jackson() }
  defaultRequest { url("http://127.0.0.1:8080/") }
  engine {
    maxConnectionsCount = 1000
    endpoint {
      connectTimeout = 30_000
      requestTimeout = 30_000
      maxConnectionsCount = 1000
      keepAliveTime = 20_000
      maxConnectionsPerRoute = 1000
      pipelineMaxSize = 20
    }
  }
}