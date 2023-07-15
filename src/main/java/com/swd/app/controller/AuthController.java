package com.swd.app.controller;

import com.swd.app.reqDto.RegisterDto;
import com.swd.cms.mapper.UserMapper;
import com.swd.common.BaseController;
import com.swd.entities.Role;
import com.swd.entities.User;
import com.swd.exception.BadRequestException;
import com.swd.model.dto.ApiMessageDto;
import com.swd.services.RoleService;
import com.swd.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/auth")
@Tag(name = "App")
public class AuthController extends BaseController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/register")
    public ApiMessageDto<Object> register(@Valid @RequestBody RegisterDto registerDto) {
        // Check if username is already taken
        if (userService.existsByUsername(registerDto.getUsername())) {
            throw new BadRequestException("Username is already taken!");
        }
        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setPassword(registerDto.getPassword());
        Role role = roleService.getByName("ROLE_USER");
        user.setRoles(Collections.singleton(role));
        User savedUser = userService.addUser(user);

        return makeResponse(true, userMapper.fromEntityToUserDto(savedUser), "Register successful!");
    }

    @PostMapping("/login")
    public ApiMessageDto<Object> login(@Valid @RequestBody RegisterDto registerDto) {
        // Check if username is already taken
        if (!userService.existsByUsername(registerDto.getUsername())) {
            throw new BadRequestException("Username is not exist!");
        }
        User user = userService.getByUsername(registerDto.getUsername());
        if (!user.getPassword().equals(registerDto.getPassword())) {
            throw new BadRequestException("Password is not correct!");
        }
        return makeResponse(true, userMapper.fromEntityToUserDto(user), "Login successful!");
    }


}
