package load_test

import io.gatling.javaapi.core.CoreDsl.*
import io.gatling.javaapi.core.Simulation
import io.gatling.javaapi.http.HttpDsl.http
import io.gatling.javaapi.http.HttpDsl.status
import kotlin.random.Random

//val baseUrl = "http://localhost:8081/" // WebMvc
val baseUrl = "http://localhost:8082/" // WebFlux
//val baseUrl = "http://localhost:8083/" // Virtual Threads
//val baseUrl = "http://localhost:8084/" // Kotlin Coroutines

class LoadScenario : Simulation() {
  init {
    val scn =
      scenario("Load Test Scenario")
        .repeat(1).on(
          exec(
            http("Get workout suggestions")
              .get("$baseUrl/users/${Random.nextInt()}/workout-suggestions")
              .check(status().shouldBe(200))
          )
        )

    setUp(scn.injectOpen(atOnceUsers(1_000)))
  }
}