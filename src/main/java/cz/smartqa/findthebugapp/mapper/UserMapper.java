package cz.smartqa.findthebugapp.mapper;

import cz.smartqa.findthebugapp.db.model.UserEntity;
import cz.smartqa.findthebugapp.dto.UserDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto userEntityToUserDto(UserEntity userEntity);

    @InheritInverseConfiguration
    UserEntity userDtoToUserEntity(UserDto userDto);
}
