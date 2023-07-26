package com.swd.app.reqDto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class AvailableTableAtTimeDto {
    private Date time;
}
