package com.swd.app.reqDto;

import lombok.Data;

@Data
public class ReservationUpdateDto {
    private Long userId;
    private Long tableId;
    private String status;
}
