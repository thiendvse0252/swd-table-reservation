package com.swd.cms.requestDto;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
public class TestingCreateFormDto {

    @NotBlank
    @Size(max = 255)
    private String name;

    private String description;
}
