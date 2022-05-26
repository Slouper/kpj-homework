package cz.inventi.homework;

import cz.inventi.homework.base.TestBase;
import cz.inventi.homework.dto.ServiceDto;
import cz.inventi.homework.service.impl.ServiceRegistrationServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;

import java.util.List;

import static cz.inventi.homework.utils.MessageUtils.getServiceName;
import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

class MessagingMainHomeworkAppTests extends TestBase {

    @Value("${self.registration.message.content}")
    private String selfRegistrationMessageContent;

    @Autowired
    private ServiceRegistrationServiceImpl serviceRegistrationService;

    @Test
    public void thisServiceSuccessfullyRegisteredTest() {
        ServiceDto service = getRestTemplate().getForObject("/services/{name}", ServiceDto.class, getServiceName(selfRegistrationMessageContent));

        if (isNull(service)) {
            throw new AssertionError(format("It is expected that the service [%s] is registered but it is not.", selfRegistrationMessageContent));
        }
    }

    @Test
    public void serviceIsRegisteredOnlyOnceTest() {
        getRestTemplate().exchange("/services/register", HttpMethod.POST, null, String.class);

        List<ServiceDto> services = asList(requireNonNull(getRestTemplate().getForObject("/services", ServiceDto[].class)));

        if (services.size() > 1) {
            throw new AssertionError(format("It is expected that the service [%s] is registered only once but it is present multiple times [%s] in the registry.",
                    selfRegistrationMessageContent, services.size()));
        }
    }
}
