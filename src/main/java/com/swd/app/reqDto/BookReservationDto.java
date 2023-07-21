package com.swd.app.reqDto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class BookReservationDto {
    @NotNull
    private Long tableId;
    @NotNull
    private Long userId;
    @NotNull
    private Date startTime;
    @NotNull
    private Date endTime;
    private Integer partySize = 1;
}
