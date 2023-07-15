package com.swd.app.reqDto;

import lombok.Data;

import java.time.Instant;
import java.util.Locale;

@Data
public class BookReservationDto {
    private Long tableId;
    private Long userId;
    private Instant startTime;
    private Instant endTime;
    private Integer partySize;
}
