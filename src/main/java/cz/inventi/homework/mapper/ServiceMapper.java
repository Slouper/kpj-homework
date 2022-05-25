package cz.inventi.homework.mapper;

import cz.inventi.homework.db.model.ServiceEntity;
import cz.inventi.homework.dto.ServiceDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServiceMapper {

    ServiceDto serviceEntityToServiceDto(ServiceEntity serviceEntity);

    @InheritInverseConfiguration
    ServiceEntity serviceDtoToServiceEntity(ServiceDto serviceDto);
}
