package com.swd.cms.controllers;

import com.swd.cms.dto.TableDto;
import com.swd.cms.mapper.TableMapper;
import com.swd.entities.Tables;
import com.swd.exception.BadRequestException;
import com.swd.repositories.TableRepository;
import com.swd.services.TableService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tables")
@Tag(name = "Table", description = "Table API")
public class TableController {
    private final TableRepository tableRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TableMapper mapper;
    @Autowired
    private TableService tableService;

    public TableController(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    @GetMapping
    public List<TableDto> getAllTables(){
        List<Tables> table = tableService.getAll();
        List<TableDto> tabDto = mapper.fromEntityToTableDtoList(table);
        return tabDto;
    }

    @GetMapping("/{id}")
    public TableDto getTableById(@PathVariable Long id){
        Tables table = tableService.getById(id);
        TableDto tabDto = mapper.fromEntityToTableDto(table);
        return tabDto;
    }

    @PostMapping()
    public TableDto addTable(@RequestBody TableDto tableDto) {
        Tables res = modelMapper.map(tableDto, Tables.class);
        Tables tableCreate = tableService.saveTable(res);
        TableDto tabDto = modelMapper.map(tableCreate, TableDto.class);
        return tabDto;
    }

    @PutMapping()
    public TableDto updateTable(@Valid @RequestBody TableDto tableDto) {
        Tables tables = tableService.getById(tableDto.getTable_id());
        tables.setBooked(tableDto.isBooked());
        tables.setCapacity(tableDto.getCapacity());
        tables.setRestaurant(tableDto.getRestaurant());
        Tables reservationUpdate= tableService.saveTable(tables);
        TableDto reservationDto = modelMapper.map(reservationUpdate, TableDto.class);
        return reservationDto;
    }

    @DeleteMapping("/{id}")
    public void deleteTable(@PathVariable(value = "id") Long id) {
        if(Boolean.FALSE.equals(tableService.existsById(id))) {
            throw new BadRequestException("Table not found");
        }
        tableService.deleteTableById(id);
    }
}
