package com.swd.app.reqDto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class BookReservationDto {
    private Long tableId;
    private Long userId;
    private Date startTime;
    private Date endTime;
    private Integer partySize;
}
