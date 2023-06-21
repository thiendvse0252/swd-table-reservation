package com.swd.cms.requestDto;

import com.swd.entities.Restaurant;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TablesCreateFormDto {

    @NotBlank
    @Size(max = 255)
    private boolean isBooked;
    private int capacity;
    private Restaurant restaurant;
}
