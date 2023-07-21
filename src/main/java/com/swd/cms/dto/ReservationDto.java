package com.swd.cms.dto;

import com.swd.entities.Restaurant;
import lombok.Data;

import java.util.Date;

@Data
public class ReservationDto {
    private Date reservationDate;
    private Integer partySize;
    private Long userId;
    private Long tableId;
    private Restaurant restaurant;
}
