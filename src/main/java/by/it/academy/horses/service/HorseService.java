package by.it.academy.horses.service;

import by.it.academy.horses.filter.HorseFilter;
import by.it.academy.horses.service.dto.HorseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface HorseService {

    List<HorseDto> findAll();

    Page<HorseDto> findAllPaginated(HorseFilter horseFilter, int pageNumber, int pageSize, String sortField, String sortDirection);

    HorseDto findById(Long horseId);

    void save(HorseDto horseDto);

    void deleteById(Long horseId);


}
