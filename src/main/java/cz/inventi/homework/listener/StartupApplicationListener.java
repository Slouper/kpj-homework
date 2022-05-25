package cz.inventi.homework.listener;

import cz.inventi.homework.messaging.impl.RabbitMQServiceImpl;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class StartupApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    @Value("${self.registration.message.content}")
    private String selfRegistrationMessageContent;

    private final RabbitMQServiceImpl rabbitMQService;

    @Override
    public void onApplicationEvent(@NonNull ContextRefreshedEvent event) {
        rabbitMQService.sendMessage(selfRegistrationMessageContent);
        log.info("Self registration message sent for the service {}.", selfRegistrationMessageContent);
    }
}