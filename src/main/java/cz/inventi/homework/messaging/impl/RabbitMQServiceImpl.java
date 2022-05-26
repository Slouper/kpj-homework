package cz.inventi.homework.messaging.impl;

import cz.inventi.homework.db.model.ServiceEntity;
import cz.inventi.homework.db.repository.ServiceRepository;
import cz.inventi.homework.messaging.MessagingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static cz.inventi.homework.utils.MessageUtils.getServiceName;
import static cz.inventi.homework.utils.MessageUtils.getServicePort;

@SuppressWarnings("unused")
@Component
@RequiredArgsConstructor
@Slf4j
public class RabbitMQServiceImpl implements MessagingService {
    private static final String QUEUE_NAME_PROPERTY_NAME = "${rabbitmq.queue.name}";
    private static final String FANOUT_NAME_PROPERTY_NAME = "${rabbitmq.fanout.name}";
    private static final String THIS_SERVICE_REGISTRATION_MESSAGE_PROPERTY_NAME = "${self.registration.message.content}";

    @Value(FANOUT_NAME_PROPERTY_NAME)
    private String fanoutName;

    @Value(QUEUE_NAME_PROPERTY_NAME)
    private String queueName;

    @Value(THIS_SERVICE_REGISTRATION_MESSAGE_PROPERTY_NAME)
    private String selfRegistrationMessageContent;

    private final RabbitTemplate rabbitTemplate;
    private final ServiceRepository serviceRepository;

    @Bean
    public FanoutExchange kpjFanout() {
        return new FanoutExchange(fanoutName, true, false);
    }

    @Bean
    public Queue kpjQueue() {
        return new Queue(queueName);
    }

    @Bean
    public Binding listenerBinding(
            @Qualifier("kpjQueue") Queue queue,
            @Qualifier("kpjFanout") FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }

    @Override
    @RabbitListener(bindings = @QueueBinding(
            value = @org.springframework.amqp.rabbit.annotation.Queue(
                    name = QUEUE_NAME_PROPERTY_NAME),
            exchange = @Exchange(
                    name = FANOUT_NAME_PROPERTY_NAME,
                    type = ExchangeTypes.FANOUT)))
    public void receiveMessage(String message) {
        log.info("Message received: {}", message);
        if (!serviceAlreadyExists(message)) {
            serviceRepository.save(new ServiceEntity(0, getServiceName(message), getServicePort(message), LocalDateTime.now()));
            sendMessage(selfRegistrationMessageContent);
            log.info("Self registration message sent for this service {}.", selfRegistrationMessageContent);
        } else {
            log.info("The service [{}] is already registered.", message);
        }
    }

    private boolean serviceAlreadyExists(String message) {
        return serviceRepository.findAll().stream().anyMatch(serviceEntity -> serviceEntity.getName().equals(getServiceName(message)));
    }

    @Override
    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(fanoutName, "", message);
    }
}
