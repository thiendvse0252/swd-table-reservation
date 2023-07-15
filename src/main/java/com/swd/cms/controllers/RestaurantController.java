package com.swd.cms.controllers;

import com.swd.cms.dto.RestaurantDto;
import com.swd.cms.mapper.RestaurantMapper;
import com.swd.common.BaseController;
import com.swd.entities.Restaurant;
import com.swd.exception.BadRequestException;
import com.swd.repositories.RestaurantRepository;
import com.swd.services.RestaurantService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
@Tag(name = "CMS")
public class RestaurantController extends BaseController {

    private final RestaurantRepository restaurantRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RestaurantMapper mapper;
    @Autowired
    private RestaurantService restaurantService;

    public RestaurantController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @PostMapping()
    public RestaurantDto addRestaurant(@Valid @RequestBody RestaurantDto resDto) {
        Restaurant res = modelMapper.map(resDto, Restaurant.class);
        Restaurant resCreate = restaurantService.addRestaurant(res);
        RestaurantDto restDto = modelMapper.map(resCreate, RestaurantDto.class);
        return restDto;
    }

    @PutMapping()
    public RestaurantDto updateRestaurant(@Valid @RequestBody RestaurantDto resDto) {
        Restaurant restaurant = restaurantService.getById(resDto.getId());
        restaurant.setName(resDto.getName());
        restaurant.setAddress(resDto.getAddress());
        Restaurant resUpdate= restaurantService.addRestaurant(restaurant);
        RestaurantDto restaurantDto = modelMapper.map(resUpdate, RestaurantDto.class);
        return restaurantDto;
    }

    @GetMapping()
    public List<RestaurantDto> getAll() {
        List<Restaurant> res = restaurantService.getAllRestaurants();
        List<RestaurantDto> resDto = mapper.fromEntityToRestaurantDtoList(res);
        //return makeResponse(true, testingDto, "Get testing detail successful!");
        return resDto;
    }

    @GetMapping("/{id}")
    public RestaurantDto getById(@PathVariable Long id) {
        Restaurant res = restaurantService.getById(id);
        RestaurantDto resDto = mapper.fromEntityToRestaurantDto(res);
        //return makeResponse(true, testingDto, "Get testing detail successful!");
        return resDto;
    }

    @DeleteMapping("/{id}")
    public void deleteRestaurant(@PathVariable(value = "id") Long id) {
        if(Boolean.FALSE.equals(restaurantService.existsById(id))) {
            throw new BadRequestException("Restaurant not found");
        }
        restaurantService.deleteRestaurantById(id);
    }
}
