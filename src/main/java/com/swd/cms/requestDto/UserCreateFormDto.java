package com.swd.cms.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserCreateFormDto {

    @NotBlank
    @Size(max = 255)
    private String firstName;

    private String lastName;
    private String phone;
    private String email;
}
