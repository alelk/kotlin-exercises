
fun main() {


  val thread1 = Thread {
    Thread.sleep(1000)
    println(Thread.currentThread().javaClass.name)
  }
  thread1.start()


  val thread2: Thread = Thread.startVirtualThread {
    Thread.sleep(1000)
    println(Thread.currentThread().javaClass.name)
  }


  thread1.join()
  thread2.join()

}