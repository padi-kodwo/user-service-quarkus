package org.tech11.service.impl;


import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tech11.dto.CreateUser;
import org.tech11.dto.EditPasswordDto;
import org.tech11.dto.EditUserDto;
import org.tech11.entity.User;
import org.tech11.exception.ServiceException;
import org.tech11.service.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final ModelMapper modelMapper = new ModelMapper();


    @Transactional
    @Override
    public User getUser(String id) {
        User user = User.findById(id);
        if (user == null)
            throw new ServiceException(-1, "no user found with id " + id);
        return user;
    }

    @Transactional
    @Override
    public User getByUsername(String username) {
        User user = User.findByUsername(username);
        if (user == null)
            throw new ServiceException(-1, "no user found with username " + username);
        return user;
    }

    @Transactional
    @Override
    public List<User> getAllUsers() {
         return User.findAll().list();
    }

    @Transactional
    @Override
    public User create(CreateUser createUser) {
        User user = modelMapper.map(createUser, User.class);

        if (!createUser.getConfirmPassword().equals(createUser.getPassword()))
            throw new ServiceException(-1,
                    "user passwords don't match");

        if (User.findByUsername(createUser.getEmail()) != null)
            throw new ServiceException(-1,
                    "users already exists");

        user.setUsername(createUser.getEmail());
        user.setPassword(createUser.getPassword()); // no encoding because it non functional requirement atm
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(createUser.getDateOfBirth(), formatter);
        user.setDateOfBirth(localDate);

        formatter = DateTimeFormatter.ofPattern("dd-MM");
        user.setBirthday(formatter.format(localDate));

        user.persist();
        return user;
    }

    @Transactional
    @Override
    public User updateUser(EditUserDto editUserDto) {
        User user = User.findById(editUserDto.getId());
        user.setFirstName(editUserDto.getFirstName());
        user.setLastName(editUserDto.getLastName());
        user.setOtherNames(editUserDto.getOtherNames());
        user.setPhone(editUserDto.getPhone());

        return user;
    }

    @Transactional
    @Override
    public User updateUserPassword(EditPasswordDto editPasswordDto) {
        User user = User.findById(editPasswordDto.getId());

        if (!editPasswordDto.getConfirmPassword().equals(editPasswordDto.getPassword()))
            throw new ServiceException(-1,
                    "user passwords don't match");

        user.setPassword(editPasswordDto.getPassword());

        return user;
    }

    @Override
    public String deleteUser(String id) {

        User user = getUser(id);
        user.delete();

        return "user with id: " + id + " deleted";
    }

    @Transactional
    @Override
    public long userCount() {
        return User.count();
    }
}