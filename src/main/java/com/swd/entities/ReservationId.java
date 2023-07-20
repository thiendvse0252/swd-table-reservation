package com.swd.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
@Data
@Embeddable
public class ReservationId implements Serializable {
    private Long userId;
    private Long tableId;
}
