package info.nt.hase.amqp

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class RabbitRunner(
        private val receiver: Receiver,
        private val rabbitTemplate: RabbitTemplate,
        @Value("\${amqp.topic.exchange.name}") private val exchange: String
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        System.out.println("Sending message...")
        rabbitTemplate.convertAndSend(exchange, "foo.bar.baz", "Hallo, süßer Hase!")
        receiver.latch.await(10000, TimeUnit.MILLISECONDS)
    }

}