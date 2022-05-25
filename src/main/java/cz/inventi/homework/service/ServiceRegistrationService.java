package cz.inventi.homework.service;

import cz.inventi.homework.dto.ServiceDto;

import java.util.List;

public interface ServiceRegistrationService {

    void registerService();

    List<ServiceDto> getServices();

    ServiceDto getServiceInfo(String name);

    ServiceDto getCurrentServiceInfo();
}
