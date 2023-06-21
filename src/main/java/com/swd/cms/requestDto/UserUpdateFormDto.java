package com.swd.cms.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserUpdateFormDto {

    @NotNull
    private Long id;

    @NotBlank
    @Size(max = 255)
    private String firstName;

    private String lastName;
    private String phone;
    private String email;
}
