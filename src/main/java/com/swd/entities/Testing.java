package com.swd.entities;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "testing")
@Getter
@Setter
public class Testing extends Auditable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Lob
    @Column(name = "description")
    private String description;
}
