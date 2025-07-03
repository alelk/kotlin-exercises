import kotlinx.coroutines.runBlocking

fun main() {

  runBlocking {


    coroutineScope {
      val v1 = async { 1 }
      val v2 = async { 2 }
      println("1 + 2 = ${v1.await() + v2.await()}")
    }


  }
}