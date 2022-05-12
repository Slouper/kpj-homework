package cz.smartqa.findthebugapp.service.impl;

import cz.smartqa.findthebugapp.db.repository.UserRepository;
import cz.smartqa.findthebugapp.dto.UserDto;
import cz.smartqa.findthebugapp.mapper.UserMapper;
import cz.smartqa.findthebugapp.service.UserRegistrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserRegistrationServiceImpl implements UserRegistrationService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto registerUser(UserDto user) {
        log.debug("Following user registered: {}", user);
        return userMapper.userEntityToUserDto(userRepository.save(userMapper.userDtoToUserEntity(user)));
    }

    @Override
    public List<UserDto> getUsers() {
        return userRepository.findAll().stream().map(userMapper::userEntityToUserDto).collect(Collectors.toList());
    }
}
