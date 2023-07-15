package com.swd.app.controller;

import com.swd.app.reqDto.BookReservationDto;
import com.swd.cms.mapper.ReservationMapper;
import com.swd.common.BaseController;
import com.swd.entities.Reservation;
import com.swd.exception.BadRequestException;
import com.swd.model.dto.ApiMessageDto;
import com.swd.services.ReservationService;
import com.swd.services.TableService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/reservation")
@Tag(name = "App")
public class AppReservationController extends BaseController {
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private TableService tableService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ReservationMapper mapper;

    @PostMapping("/book")
    public ApiMessageDto<Object> bookTable(@Valid @RequestBody BookReservationDto bookReservationDto){
        // Check if table exists
        if(!tableService.existsById(bookReservationDto.getTableId())) {
            throw new BadRequestException("Table does not exist");
        }
        // Check if user exists
        if(!reservationService.existsById(bookReservationDto.getUserId())) {
            throw new BadRequestException("User does not exist");
        }
        // Check if table is booked
        if(tableService.isBooked(bookReservationDto.getTableId())){
            throw new BadRequestException("Table is already booked");
        }
        Reservation reservation = modelMapper.map(bookReservationDto, Reservation.class);
        // Set table to booked
        reservation.setTable(tableService.getById(bookReservationDto.getTableId()));
        // Check if start time is before end time
        if(reservation.getStartTime().isAfter(reservation.getEndTime())){
            throw new BadRequestException("Start time is after end time");
        }
        // Check if party size is greater than table capacity
        if(reservation.getPartySize() > reservation.getTable().getCapacity()){
            throw new BadRequestException("Party size is greater than table capacity");
        }
        Reservation savedReservation = reservationService.addReservation(reservation);
        return makeResponse(true,mapper.fromEntityToReservationDto(savedReservation), "Reservation added successfully");
    }
}
