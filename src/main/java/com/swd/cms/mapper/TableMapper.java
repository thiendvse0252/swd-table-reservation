package com.swd.cms.mapper;

import com.swd.cms.dto.TableDto;
import com.swd.entities.Tables;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TableMapper {
    @IterableMapping(elementTargetType = TableDto.class, qualifiedByName = "fromEntityToTableDto")
    @Named(value = "fromEntityToTableDtoList")
    public List<TableDto> fromEntityToTableDtoList(List<Tables> input);

    @Named(value = "fromEntityToTableDto")
    @Mapping(target = "restaurantId", source = "restaurant.id")
    public TableDto fromEntityToTableDto(Tables input);
}
