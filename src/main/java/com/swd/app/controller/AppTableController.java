package com.swd.app.controller;

import com.swd.cms.dto.TableDto;
import com.swd.cms.mapper.TableMapper;
import com.swd.common.BaseController;
import com.swd.entities.Tables;
import com.swd.model.dto.ApiMessageDto;
import com.swd.services.TableService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
