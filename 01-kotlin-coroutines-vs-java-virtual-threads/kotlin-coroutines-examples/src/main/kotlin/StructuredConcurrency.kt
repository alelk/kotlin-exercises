package kotlin_coroutines_example

import kotlinx.coroutines.*

fun main() {

  runBlocking {
    launch { // 0
      delay(100_000)
    }
    async { // 1
      someFunc()
    }
  }
}


suspend fun someFunc() {
  coroutineScope {
    launch { // 2

      delay(100)

      launch { // 3
        delay(30_000)
      }

      launch { // 4
        delay(40_000)
      }
    }

    val r1 = async {  // 5
      delay(50_000)
      1
    }

    val r2 = async {  // 6
      delay(60_000)

      launch {  // 7
        delay(70_000)
      }

      2
    }

    r1.await() + r2.await()
  }
}