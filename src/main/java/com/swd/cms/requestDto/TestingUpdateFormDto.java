package com.swd.cms.requestDto;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
public class TestingUpdateFormDto {

    @NotNull
    private Long id;

    @NotBlank
    @Size(max = 255)
    private String name;

    private String description;
}
