package com.swd.entities;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Setter
@Getter
public class UserTableKey implements Serializable {
    private long userId;
    private long tableId;
}
