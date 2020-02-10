package m.plywacz.exchangeratesapi.services;
/*
Author: BeGieU
Date: 09.02.2020
*/

import m.plywacz.exchangeratesapi.dto.UserDto;
import m.plywacz.exchangeratesapi.exceptions.ResourceNotFoundException;
import m.plywacz.exchangeratesapi.model.User;
import m.plywacz.exchangeratesapi.repo.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override public User saveUser(UserDto userDto) {
        User user = convertDto(userDto);
        return userRepo.save(user);
    }

    private User convertDto(UserDto userDto) {
        //todo add exception handling

        var user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        return user;
    }

    @Override public User listUser(Long userId) {
        return userRepo.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("user with id: " + userId));
    }
}
