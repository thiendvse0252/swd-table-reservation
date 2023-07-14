package com.swd.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
@Getter
@Setter
public class Reservation {
    @EmbeddedId
    private UserTableKey id;

    @Column(name = "res_date")
    private LocalDateTime resDate;

    @Column(name = "party_size")
    private int partySize;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private Tables table;

}

