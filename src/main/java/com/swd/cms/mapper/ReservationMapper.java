package com.swd.cms.mapper;

import com.swd.app.reqDto.BookReservationDto;
import com.swd.cms.dto.ReservationDto;
import com.swd.entities.Reservation;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    @IterableMapping(elementTargetType = ReservationDto.class, qualifiedByName = "fromEntityToReservationDto")
    @Named(value = "fromEntityToReservationDtoList")
    public List<ReservationDto> fromEntityToReservationDtoList(List<Reservation> input);

    @Named(value = "fromEntityToReservationDto")
    public ReservationDto fromEntityToReservationDto(Reservation input);

    @Named(value = "fromEntityToBookReservationDto")
    @Mapping(target = "tableId", source = "table.id")
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "startTime", source = "startTime")
    @Mapping(target = "endTime", source = "endTime")
    @Mapping(target = "partySize", source = "partySize")

    public BookReservationDto fromEntityToBookReservationDto(Reservation input);
}
