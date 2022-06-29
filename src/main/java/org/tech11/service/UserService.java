package org.tech11.service;

import org.tech11.dto.CreateUser;
import org.tech11.dto.EditPasswordDto;
import org.tech11.dto.EditUserDto;
import org.tech11.entity.User;

import java.util.List;


public interface UserService {

    User getUser(String id);

    User getByUsername(String username);

    List<User> getAllUsers();

    User create(CreateUser createUser);

    User updateUser(EditUserDto editUserDto);

    User updateUserPassword(EditPasswordDto editPasswordDto);

    String deleteUser(String id);

    long userCount();
}
