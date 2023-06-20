package com.swd.cms.dto;

import com.swd.entities.Restaurant;
import lombok.Data;

@Data
public class TableDto {
    private Long table_id;
    private int capacity;
    private boolean isBooked;
    private Restaurant restaurant;
}
