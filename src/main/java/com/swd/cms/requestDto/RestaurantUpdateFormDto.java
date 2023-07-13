package com.swd.cms.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RestaurantUpdateFormDto {

    @NotNull
    private Long id;

    @NotBlank
    @Size(max = 255)
    private String name;

    private String address;
}
