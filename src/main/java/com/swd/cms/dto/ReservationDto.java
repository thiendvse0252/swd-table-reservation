package com.swd.cms.dto;

import com.swd.constraints.EReservationStatus;
import com.swd.entities.Restaurant;
import com.swd.entities.Tables;
import com.swd.entities.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
public class ReservationDto {
    private Long id;
    private Long userId;
    private Long tableId;
    private Date reservationDate;
    private Date startTime;
    private Date endTime;
    private int partySize;
    private EReservationStatus status;
}
