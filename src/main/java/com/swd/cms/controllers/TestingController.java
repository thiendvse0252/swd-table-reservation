package com.swd.cms.controllers;

import com.swd.cms.criteria.TestingCriteria;
import com.swd.cms.dto.TestingDto;
import com.swd.cms.mapper.TestingMapper;
import com.swd.cms.requestDto.TestingCreateFormDto;
import com.swd.cms.requestDto.TestingUpdateFormDto;
import com.swd.entities.Testing;
import com.swd.exception.BadRequestException;
import com.swd.model.dto.ApiMessageDto;
import com.swd.model.dto.ResponseListDto;
import com.swd.services.TestingService;

import jakarta.validation.Valid;

import com.swd.common.BaseController;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/testing")
public class TestingController extends BaseController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TestingMapper mapper;
    @Autowired
    private TestingService testingService;

    @PostMapping()
    public ApiMessageDto<Object> createTesting(@Valid @RequestBody TestingCreateFormDto testingCreateFormDto) {
        Testing testing = modelMapper.map(testingCreateFormDto, Testing.class);
        Testing testingCreate = testingService.saveTesting(testing);
        TestingDto testingDto = modelMapper.map(testingCreate, TestingDto.class);
        return makeResponse(true, testingDto, "Create testing successfully");
    }

    @PutMapping()
    public ApiMessageDto<Object> updateTesting(@Valid @RequestBody TestingUpdateFormDto testingUpdateFormDto) {
        Testing testing = testingService.getById(testingUpdateFormDto.getId());
        testing.setName(testingUpdateFormDto.getName());
        testing.setDescription(testingUpdateFormDto.getDescription());
        Testing testingUpdate= testingService.saveTesting(testing);
        TestingDto testingDto = modelMapper.map(testingUpdate, TestingDto.class);
        return makeResponse(true, testingDto, "Update testing successfully");
    }

    @GetMapping()
    public ApiMessageDto<Object> getTestings(TestingCriteria criteria, Pageable pageable) {
        Page<Testing> testingPage = testingService.getByCriteria(criteria, pageable);
        List<TestingDto> testingDtos = mapper.fromEntityToTestingDtoList(testingPage.getContent());
        ResponseListDto<TestingDto> responseListDto = new ResponseListDto<>(testingDtos, testingPage.getNumber(), testingPage.getSize(), testingPage.getTotalElements(), testingPage.getTotalPages(), testingPage.isLast());
        return makeResponse(true, responseListDto, "Get testing list successfully!");
    }

    @GetMapping("/{id}")
    public ApiMessageDto<Object> getById(@PathVariable(value = "id") Long id) {
        Testing testing = testingService.getById(id);
        TestingDto testingDto = mapper.fromEntityToTestingDto(testing);
        return makeResponse(true, testingDto, "Get testing detail successful!");
    }

    @DeleteMapping("/{id}")
    public ApiMessageDto<Object> deleteAttribute(@PathVariable(value = "id") Long id) {
        if(Boolean.FALSE.equals(testingService.existsById(id))) {
            throw new BadRequestException("Testing not found");
        }
        testingService.deleteTestingById(id);
        return makeResponse(true, null, "Delete testing successful!");
    }
}
