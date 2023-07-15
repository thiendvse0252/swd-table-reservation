package com.swd.cms.dto;

import com.swd.entities.Restaurant;
import com.swd.entities.Tables;
import com.swd.entities.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservationDto {
//    private Long id;
    private LocalDateTime resDate;
    private Integer partySize;
    private Long userId;
    private Long tableId;
    private Restaurant restaurant;
}
