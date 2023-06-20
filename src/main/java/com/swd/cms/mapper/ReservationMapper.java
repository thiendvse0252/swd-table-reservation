package com.swd.cms.mapper;

import com.swd.cms.dto.ReservationDto;
import com.swd.entities.Reservation;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    @IterableMapping(elementTargetType = ReservationDto.class, qualifiedByName = "fromEntityToReservationDto")
    @Named(value = "fromEntityToReservationDtoList")
    public List<ReservationDto> fromEntityToReservationDtoList(List<Reservation> input);

    @Named(value = "fromEntityToReservationDto")
    public ReservationDto fromEntityToReservationDto(Reservation input);
}
