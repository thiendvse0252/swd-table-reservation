package com.swd.cms.mapper;

import com.swd.cms.dto.TestingDto;
import com.swd.entities.Testing;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-19T14:13:07+0700",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.34.0.v20230511-1142, environment: Java 17.0.7 (Eclipse Adoptium)"
)
@Component
public class TestingMapperImpl implements TestingMapper {

    @Override
    public List<TestingDto> fromEntityToTestingDtoList(List<Testing> input) {
        if ( input == null ) {
            return null;
        }

        List<TestingDto> list = new ArrayList<TestingDto>( input.size() );
        for ( Testing testing : input ) {
            list.add( fromEntityToTestingDto( testing ) );
        }

        return list;
    }

    @Override
    public TestingDto fromEntityToTestingDto(Testing input) {
        if ( input == null ) {
            return null;
        }

        TestingDto testingDto = new TestingDto();

        testingDto.setDescription( input.getDescription() );
        testingDto.setId( input.getId() );
        testingDto.setName( input.getName() );

        return testingDto;
    }
}
