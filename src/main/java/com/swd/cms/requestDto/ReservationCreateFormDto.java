package com.swd.cms.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ReservationCreateFormDto {

    @NotBlank
    @Size(max = 255)
    private String name;

    private String description;
}
