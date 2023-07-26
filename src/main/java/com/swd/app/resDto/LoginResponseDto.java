package com.swd.app.resDto;

import lombok.Data;

import java.util.List;

@Data
public class LoginResponseDto {
    private Long id;
    private String username;
    private String token;
    private String fullName;
    private List<String> roles;
}
