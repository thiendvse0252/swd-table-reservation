package com.swd.cms.mapper;

import com.swd.cms.dto.RestaurantDto;
import com.swd.entities.Restaurant;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    @IterableMapping(elementTargetType = RestaurantDto.class, qualifiedByName = "fromEntityToRestaurantDto")
    @Named(value = "fromEntityToRestaurantDtoList")
    public List<RestaurantDto> fromEntityToRestaurantDtoList(List<Restaurant> input);

    @Named(value = "fromEntityToRestaurantDto")
    public RestaurantDto fromEntityToRestaurantDto(Restaurant input);
}
