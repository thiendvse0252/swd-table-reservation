package com.swd.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Data
@Embeddable
public class UserTableKey implements Serializable {
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "table_id")
    private Long tableId;
}
