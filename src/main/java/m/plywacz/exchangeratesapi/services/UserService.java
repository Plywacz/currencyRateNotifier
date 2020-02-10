package m.plywacz.exchangeratesapi.services;
/*
Author: BeGieU
Date: 09.02.2020
*/

import m.plywacz.exchangeratesapi.dto.UserDto;
import m.plywacz.exchangeratesapi.model.User;

public interface UserService {
    User saveUser(UserDto user);

    User listUser(Long userId);

}
