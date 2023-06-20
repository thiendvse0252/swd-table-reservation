package com.swd.cms.dto;

import com.swd.entities.Restaurant;
import com.swd.entities.Tables;
import com.swd.entities.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservationDto {
    private long reservation_id;
    private LocalDateTime resDate;
    private int partySize;
    private User user;
    private Tables table;
    private Restaurant restaurant;
}
