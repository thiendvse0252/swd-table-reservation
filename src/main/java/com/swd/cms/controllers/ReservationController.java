package com.swd.cms.controllers;

import com.swd.cms.dto.ReservationDto;
import com.swd.cms.mapper.ReservationMapper;
import com.swd.common.BaseController;
import com.swd.entities.Reservation;
import com.swd.exception.BadRequestException;
import com.swd.repositories.ReservationRepository;
import com.swd.services.ReservationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
@Tag(name = "CMS")
public class ReservationController extends BaseController {

    private final ReservationRepository reservationRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ReservationMapper mapper;
    @Autowired
    private ReservationService reservationService;

    public ReservationController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @PostMapping()
    public ReservationDto addReservation(@RequestBody ReservationDto reserveDto) {
        Reservation res = modelMapper.map(reserveDto, Reservation.class);
        Reservation reserveCreate = reservationService.addReservation(res);
        ReservationDto resDto = modelMapper.map(reserveCreate, ReservationDto.class);
        return resDto;
    }

    @PutMapping()
    public ReservationDto updateReservation(@Valid @RequestBody ReservationDto reserveDto) {
        Reservation reservation = reservationService.getByUserIdAndTableId(reserveDto.getUserId(), reserveDto.getTableId());
        reservation.setPartySize(reserveDto.getPartySize());
        reservation.setReservationDate(reserveDto.getReservationDate());
//        reservation.setTable(reserveDto.getTable());
//        reservation.setUser(reserveDto.getUser());
        Reservation reservationUpdate= reservationService.addReservation(reservation);
        ReservationDto reservationDto = modelMapper.map(reservationUpdate, ReservationDto.class);
        return reservationDto;
    }

    @GetMapping()
    public List<ReservationDto> getAll() {
        List<Reservation> res = reservationService.getAll();
        List<ReservationDto> resDto = mapper.fromEntityToReservationDtoList(res);
        //return makeResponse(true, testingDto, "Get testing detail successful!");
        return resDto;
    }

    @GetMapping("/{id}")
    public ReservationDto getById(@PathVariable Long id) {
        Reservation res = reservationService.getById(id);
        ReservationDto resDto = mapper.fromEntityToReservationDto(res);
        //return makeResponse(true, testingDto, "Get testing detail successful!");
        return resDto;
    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable(value = "id") Long id) {
        if(Boolean.FALSE.equals(reservationService.existsById(id))) {
            throw new BadRequestException("Reservation not found");
        }
        reservationService.deleteReservationById(id);
    }
}
