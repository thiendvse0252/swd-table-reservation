package com.swd.cms.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String fullName;
    private String phone;
    private String email;
    private String username;
}
