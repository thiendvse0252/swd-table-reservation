package com.swd.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
@Getter
@Setter
public class Reservation {
    @EmbeddedId
    private UserTableKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("tableId")
    @JoinColumn(name = "table_id")
    private Tables table;

    @Column(name = "res_date")
    private LocalDateTime resDate;

    @Column(name = "start_time")
    private Instant startTime;

    @Column(name = "end_time")
    private Instant endTime;

    @Column(name = "party_size")
    private int partySize;

}

