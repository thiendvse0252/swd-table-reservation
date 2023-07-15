package com.swd.cms.dto;

import com.swd.entities.Restaurant;
import lombok.Data;

@Data
public class TableDto {
    private Long id;
    private Integer capacity;
    private Boolean isBooked;
    private Long restaurantId;
}
