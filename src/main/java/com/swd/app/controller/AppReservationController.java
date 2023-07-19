package com.swd.app.controller;

import com.swd.app.reqDto.BookReservationDto;
import com.swd.app.reqDto.ReservationUpdateDto;
import com.swd.cms.mapper.ReservationMapper;
import com.swd.common.BaseController;
import com.swd.constraints.EReservationStatus;
import com.swd.entities.Reservation;
import com.swd.entities.Tables;
import com.swd.entities.User;
import com.swd.entities.UserTableKey;
import com.swd.exception.BadRequestException;
import com.swd.model.dto.ApiMessageDto;
import com.swd.services.ReservationService;
import com.swd.services.TableService;
import com.swd.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
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
    @Autowired
    private UserService userService;

    @Transactional
    @PostMapping("/book")
    public ApiMessageDto<Object> bookTable(@Valid @RequestBody BookReservationDto bookReservationDto) {
        // Check if table exists
        if (!tableService.existsById(bookReservationDto.getTableId())) {
            throw new BadRequestException("Table does not exist");
        }
        // Check if user exists
        if (!userService.existsById(bookReservationDto.getUserId())) {
            throw new BadRequestException("User does not exist");
        }
        User user = userService.getById(bookReservationDto.getUserId());
        // Check if table is booked
        if (tableService.isBooked(bookReservationDto.getTableId())) {
            throw new BadRequestException("Table is already booked");
        }
        // Check if start time is before end time
        if (bookReservationDto.getStartTime().isAfter(bookReservationDto.getEndTime())) {
            throw new BadRequestException("Start time is after end time");
        }
        Tables table = tableService.getById(bookReservationDto.getTableId());
        // Check if party size is greater than table capacity
        if (bookReservationDto.getPartySize() > table.getCapacity()) {
            throw new BadRequestException("Party size is greater than table capacity");
        }
        UserTableKey userTableKey = new UserTableKey();
        userTableKey.setUserId(bookReservationDto.getUserId());
        userTableKey.setTableId(bookReservationDto.getTableId());
        Reservation reservation = modelMapper.map(bookReservationDto, Reservation.class);
        // Set table to booked
        reservation.setId(userTableKey);
        reservation.setTable(table);
        reservation.setUser(user);
        reservation.setStatus(EReservationStatus.PENDING);
        table.setLastCheckout(bookReservationDto.getEndTime());
        tableService.saveTable(table);
        Reservation savedReservation = reservationService.addReservation(reservation);
        return makeResponse(true, mapper.fromEntityToBookReservationDto(savedReservation), "Reservation added successfully");
    }

    // Update reservation status
    @PostMapping("/update")
    ApiMessageDto<Object> updateReservationStatus(@Valid @RequestBody ReservationUpdateDto reservationUpdateDto) {
        Reservation reservation = reservationService.getByUserIdAndTableId(reservationUpdateDto.getUserId(), reservationUpdateDto.getTableId());
        if (reservation == null) {
            throw new BadRequestException("Reservation does not exist");
        }
        if (reservationService.isValidStatus(reservationUpdateDto.getStatus())) {
            throw new BadRequestException("Invalid status");
        }
        reservation.setStatus(EReservationStatus.valueOf(reservationUpdateDto.getStatus()));
        Reservation updatedReservation = reservationService.addReservation(reservation);
        return makeResponse(true, mapper.fromEntityToReservationDto(updatedReservation), "Reservation updated successfully");
    }
}
