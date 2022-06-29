package org.tech11.controller;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tech11.domain.ApiResponse;
import org.tech11.dto.CreateUser;
import org.tech11.dto.EditPasswordDto;
import org.tech11.dto.EditUserDto;
import org.tech11.dto.UserDto;
import org.tech11.entity.User;
import org.tech11.exception.ServiceException;
import org.tech11.service.UserService;
import org.tech11.util.Utils;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("users")
@Produces(MediaType.APPLICATION_JSON)
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Inject
    UserService userService;

    private final ModelMapper modelMapper = new ModelMapper();

    @GET
    @Path("{id}")
    public ApiResponse<UserDto> getUser(@PathParam("id") String id) {

        logger.info("http request: getUser");

        User user = userService.getUser(id);
        UserDto userDto = modelMapper.map(user, UserDto.class);
        ApiResponse<UserDto> apiResponse= Utils.wrapInApiResponse(userDto);

        logger.info("http response: getUser: {}", apiResponse);

        return apiResponse;
    }

    @GET
    @Path("username/{username}")
    public ApiResponse<UserDto> getUserByUsername(@PathParam("username") String username) {

        logger.info("http request: getUserByUsername");

        User user = userService.getByUsername(username);
        UserDto userDto = modelMapper.map(user, UserDto.class);
        ApiResponse<UserDto> apiResponse= Utils.wrapInApiResponse(userDto);

        logger.info("http response: getUserByUsername: {}", apiResponse);

        return apiResponse;
    }

    @GET
    @Path("all")
    public ApiResponse<List<UserDto>> getAllUsers() {

        logger.info("http request: getAllUsers");

        List<User> users = userService.getAllUsers();
        if (users == null)
            users = new ArrayList<>();

        List<UserDto> userDtos = users
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
        ApiResponse<List<UserDto>> apiResponse= Utils.wrapInApiResponse(userDtos);

        logger.info("http response: getAllUsers: {}", apiResponse);

        return apiResponse;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public ApiResponse<UserDto> create(@Valid CreateUser createUser) {

        logger.info("http request: create");

        User user = userService.create(createUser);
        if (user == null)
            throw new ServiceException(-1, "sorry, user could not be created ");

        UserDto userDto = modelMapper.map(user, UserDto.class);
        ApiResponse<UserDto> apiResponse= Utils.wrapInApiResponse(userDto);

        logger.info("http response: create: {}", apiResponse);

        return apiResponse;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public ApiResponse<UserDto> editUser(@Valid EditUserDto editUserDto) {

        logger.info("http request: editUser");

        User user = userService.updateUser(editUserDto);
        if (user == null)
            throw new ServiceException(-1, "sorry, user could not be created ");

        UserDto userDto = modelMapper.map(user, UserDto.class);
        ApiResponse<UserDto> apiResponse= Utils.wrapInApiResponse(userDto);

        logger.info("http response: editUser: {}", apiResponse);

        return apiResponse;
    }

    @PUT
    @Path("password/reset")
    @Consumes(MediaType.APPLICATION_JSON)
    public ApiResponse<UserDto> updateUserPassword(@Valid EditPasswordDto editPasswordDto) {

        logger.info("http request: updateUserPassword");

        User user = userService.updateUserPassword(editPasswordDto);
        UserDto userDto = modelMapper.map(user, UserDto.class);
        ApiResponse<UserDto> apiResponse= Utils.wrapInApiResponse(userDto);

        logger.info("http response: updateUserPassword: {}", apiResponse);

        return apiResponse;
    }


    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public ApiResponse<String> deleteUser(@PathParam("id") String id) {

        logger.info("http request: deleteUser");

        String msg = userService.deleteUser(id);
        ApiResponse<String> apiResponse= Utils.wrapInApiResponse(msg);

        logger.info("http response: deleteUser: {}", apiResponse);

        return apiResponse;
    }

    @GET
    @Path("count")
    @Consumes(MediaType.APPLICATION_JSON)
    public ApiResponse<Long> userCount(@Valid EditPasswordDto editPasswordDto) {

        logger.info("http request: userCount");

        Long usersCount = userService.userCount();
        ApiResponse<Long> apiResponse= Utils.wrapInApiResponse(usersCount);

        logger.info("http response: userCount: {}", apiResponse);

        return apiResponse;
    }
}
