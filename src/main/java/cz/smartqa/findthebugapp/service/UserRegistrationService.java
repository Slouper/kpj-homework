package cz.smartqa.findthebugapp.service;

import cz.smartqa.findthebugapp.dto.UserDto;

import java.util.List;

public interface UserRegistrationService {

    UserDto registerUser(UserDto user);

    List<UserDto> getUsers();
}
