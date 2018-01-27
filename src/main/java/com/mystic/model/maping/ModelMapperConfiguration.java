package com.mystic.model.maping;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Pavel Putrenkov
 * @version 1.0
 * @since
 */

@Configuration
public class ModelMapperConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        //        modelMapper.addConverter(new FromTableToTableDtoConverter());
//        modelMapper.addConverter(new FromUmowaPrzechowaniaToUmowaPrzechowaniaDtoConverter());
//        modelMapper.addConverter(new FromWarehouseToWarehouseDtoConverter());
//        modelMapper.addConverter(new FromPzToPzDto());
//        modelMapper.addConverter(new FromPzoPlanPositionToWarehousePosition());
//        modelMapper.addConverter(new FromWarehousePositionToWarehousePositionDtoConverter());
//        modelMapper.addConverter(new FromWarehousePositionDtoToWarehousePositionNoPzDtoConverter());
        return modelMapper;
    }
}