package by.it.academy.horses.service.mappers;

import by.it.academy.horses.repository.Horse;
import by.it.academy.horses.service.dto.HorseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HorseMapper {
    HorseDto toHorseDto(Horse horse);

    Horse toHorse(HorseDto horseDto);
}
