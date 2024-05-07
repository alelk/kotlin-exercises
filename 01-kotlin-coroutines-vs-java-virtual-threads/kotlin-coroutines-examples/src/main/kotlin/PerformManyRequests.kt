package kotlin_coroutines_example

import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.random.Random
import kotlin.time.measureTime

fun main() {
  val t =
    measureTime {
      runBlocking {
        repeat(100_000) {
          launch {
            kotlin.runCatching {
              httpClient
                .get("/users/${Random.nextInt(10_000)}/activity-summary")
                .body<ActivitySummary>()
            }.onSuccess {
              println(it)
            }
          }
        }
      }
    }
  println("Completed 100 000 requests: duration = $t")
}

