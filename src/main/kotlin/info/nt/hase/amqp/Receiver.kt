package info.nt.hase.amqp

import org.springframework.stereotype.Component
import java.util.concurrent.CountDownLatch

@Component
class Receiver {

    val latch = CountDownLatch(1)

    fun receiveMessage(message: String) {
        System.out.println("Received $message")
        latch.countDown() //allows receiver to signal that the message is received. This is something not likely needed in a production application.
    }
}