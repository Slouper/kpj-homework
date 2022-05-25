package cz.inventi.homework.controller;

import cz.inventi.homework.dto.ServiceDto;
import cz.inventi.homework.service.impl.ServiceRegistrationServiceImpl;
import cz.inventi.homework.utils.ResponseMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = ServiceController.CONTROLLER_ROOT, produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
@RequiredArgsConstructor
@Slf4j
public class ServiceController {
    public static final String CONTROLLER_ROOT = "/services";

    private final ServiceRegistrationServiceImpl serviceRegistrationService;

    @GetMapping
    ResponseEntity<List<ServiceDto>> getServices() {
        return ResponseEntity.ok(serviceRegistrationService.getServices());
    }

    @GetMapping("/{name}")
    ResponseEntity<ServiceDto> getService(@PathVariable String name) {
        return ResponseEntity.ok(serviceRegistrationService.getServiceInfo(name));
    }

    @GetMapping("/current")
    ResponseEntity<ServiceDto> getCurrentServiceInfo() {
        return ResponseEntity.ok(serviceRegistrationService.getCurrentServiceInfo());
    }

    @PostMapping(value = "/register")
    ResponseEntity<ResponseMessage> registerService() {
        serviceRegistrationService.registerService();
        return ResponseEntity.ok(new ResponseMessage("Registration message sent successfully."));
    }
}

