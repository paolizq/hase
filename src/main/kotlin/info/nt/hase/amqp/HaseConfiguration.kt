package info.nt.hase.amqp

import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.beans.factory.annotation.Value

@Configuration
class HaseConfiguration(
        @Value("\${amqp.topic.exchange.name}") private val topicExchangeName: String,
        @Value("\${amqp.queue.name}") private val queueName: String
) {

    @Bean fun queue() = Queue(queueName, false)

    @Bean fun exchange() = TopicExchange(topicExchangeName)

    @Bean fun binding(queue: Queue, topicExchange: TopicExchange) =
            BindingBuilder
                    .bind(queue)
                    .to(exchange())
                    .with("foo.bar.#")

    @Bean fun container(connectionFactory: ConnectionFactory, listenerAdapter: MessageListenerAdapter) =
            SimpleMessageListenerContainer().apply {
                this.connectionFactory = connectionFactory
                setQueueNames(queueName)
                messageListener = listenerAdapter

            }

    @Bean fun listenerAdapter(receiver: Receiver) = MessageListenerAdapter(receiver, "receiveMessage")
}