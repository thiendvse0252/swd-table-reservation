package com.swd.cms.controllers;

import com.swd.cms.dto.UserDto;
import com.swd.cms.mapper.UserMapper;
import com.swd.common.BaseController;
import com.swd.entities.User;
import com.swd.exception.BadRequestException;
import com.swd.repositories.UserRepository;
import com.swd.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Tag(name = "CMS")
public class UserController extends BaseController {

    private final UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserMapper mapper;
    @Autowired
    private UserService userService;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping()
    public UserDto addUser(@RequestBody UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        User userCreate = userService.addUser(user);
        UserDto usDto = modelMapper.map(userCreate, UserDto.class);
        return usDto;
    }

    @PutMapping()
    public UserDto updateUser(@Valid @RequestBody UserDto userDto) {
        User users = userService.getById(userDto.getId());
        users.setEmail(userDto.getEmail());
        users.setPhone(userDto.getPhone());
        User userUpdate= userService.addUser(users);
        UserDto usersDto = modelMapper.map(userUpdate, UserDto.class);
        return usersDto;
    }

    @GetMapping()
    public List<UserDto> getAll() {
        List<User> res = userService.getAllUsers();
        List<UserDto> userDto = mapper.fromEntityToUserDtoList(res);
        //return makeResponse(true, testingDto, "Get testing detail successful!");
        return userDto;
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Long id) {
        User us = userService.getById(id);
        UserDto usDto = mapper.fromEntityToUserDto(us);
        //return makeResponse(true, testingDto, "Get testing detail successful!");
        return usDto;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable(value = "id") Long id) {
        if(Boolean.FALSE.equals(userService.existsById(id))) {
            throw new BadRequestException("User not found");
        }
        userService.deleteUserById(id);
    }
}
