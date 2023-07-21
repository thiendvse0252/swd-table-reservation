package com.swd.app.controller;

import com.swd.app.reqDto.AvailableTableAtTimeDto;
import com.swd.cms.dto.TableDto;
import com.swd.cms.mapper.TableMapper;
import com.swd.common.BaseController;
import com.swd.entities.Tables;
import com.swd.model.dto.ApiMessageDto;
import com.swd.services.TableService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/app/table")
@Tag(name = "App")
public class AppTableController extends BaseController {
    @Autowired
    private TableService tableService;

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
        List<Tables> tables = tableService.getAvailableTables(startTime, endTime);
        return makeResponse(true, null, "Get all available tables successful!");
    }
}
