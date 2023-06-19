package com.swd.cms.mapper;

import com.swd.cms.dto.TestingDto;
import com.swd.entities.Testing;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TestingMapper {
    @IterableMapping(elementTargetType = TestingDto.class, qualifiedByName = "fromEntityToTestingDto")
    @Named(value = "fromEntityToTestingDtoList")
    public List<TestingDto> fromEntityToTestingDtoList(List<Testing> input);

    @Named(value = "fromEntityToTestingDto")
    public TestingDto fromEntityToTestingDto(Testing input);
}
