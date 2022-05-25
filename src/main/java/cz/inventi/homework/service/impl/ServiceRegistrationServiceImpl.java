package cz.inventi.homework.service.impl;

import cz.inventi.homework.db.repository.ServiceRepository;
import cz.inventi.homework.dto.ServiceDto;
import cz.inventi.homework.mapper.ServiceMapper;
import cz.inventi.homework.messaging.impl.RabbitMQServiceImpl;
import cz.inventi.homework.service.ServiceRegistrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

import static cz.inventi.homework.utils.MessageUtils.getServiceName;

@Service
@RequiredArgsConstructor
@Slf4j
public class ServiceRegistrationServiceImpl implements ServiceRegistrationService {

    @Value("${self.registration.message.content}")
    private String selfRegistrationMessageContent;

    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;
    private final RabbitMQServiceImpl rabbitMQService;

    @Override
    public void registerService() {
        rabbitMQService.sendMessage(selfRegistrationMessageContent);
    }

    @Override
    public List<ServiceDto> getServices() {
        return serviceRepository.findAll().stream().map(serviceMapper::serviceEntityToServiceDto).collect(Collectors.toList());
    }

    @Override
    public ServiceDto getServiceInfo(String name) {
        return serviceMapper.serviceEntityToServiceDto(serviceRepository
                .findByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @Override
    public ServiceDto getCurrentServiceInfo() {
        return getServiceInfo(getServiceName(selfRegistrationMessageContent));
    }
}
