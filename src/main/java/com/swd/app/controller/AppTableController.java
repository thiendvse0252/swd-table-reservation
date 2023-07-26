package com.swd.app.controller;

import com.swd.app.reqDto.AvailableTableAtTimeDto;
import com.swd.app.resDto.GetAvailableTableResponseDto;
import com.swd.cms.dto.TableDto;
import com.swd.cms.mapper.TableMapper;
import com.swd.common.BaseController;
import com.swd.entities.Reservation;
import com.swd.entities.Tables;
import com.swd.model.dto.ApiMessageDto;
import com.swd.services.ReservationService;
import com.swd.services.TableService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/app/table")
@Tag(name = "App")
public class AppTableController extends BaseController {
    @Autowired
    private TableService tableService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TableMapper mapper;

    @GetMapping("/all")
    public ApiMessageDto<Object> getAll(){
        List<Tables> tables = tableService.getAll();
        List<TableDto> tableDtos = mapper.fromEntityToTableDtoList(tables);
        return makeResponse(true, tableDtos, "Get all table successful!");
    }
    // Get all available tables at a specific time
    @PostMapping("/available")
    public ApiMessageDto<Object> getAvailableTables(@RequestBody AvailableTableAtTimeDto availableTableAtTimeDto) {
        Date startTime = availableTableAtTimeDto.getTime();
        // End time is 2 hours after start time
        Date endTime = Date.from(Instant.ofEpochMilli(startTime.getTime()).plusSeconds(7200));
        List<Tables> tables = tableService.getAll();
        List<Reservation> reservations = reservationService.getAllAcceptedReservationsInTime(startTime, endTime);

        List<GetAvailableTableResponseDto> availableTableResponseDtoList = List.of(modelMapper.map(tables, GetAvailableTableResponseDto[].class));

        for (Reservation reservation : reservations) {
            for (GetAvailableTableResponseDto availableTableResponseDto : availableTableResponseDtoList) {
                if (Objects.equals(reservation.getTable().getId(), availableTableResponseDto.getId())) {
                    availableTableResponseDto.setIsBooked(true);
                }
            }
        }
        return makeResponse(true, availableTableResponseDtoList, "Get all available tables successful!");
    }
}
