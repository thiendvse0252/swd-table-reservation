package com.swd.app.resDto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class GetAvailableTableResponseDto {
    private Long id;
    private int capacity;
    private String type;
    private Boolean isBooked = false;
}
