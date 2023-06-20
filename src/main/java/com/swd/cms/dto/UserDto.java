package com.swd.cms.dto;

import lombok.Data;

@Data
public class UserDto {
    private long userId;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
}
