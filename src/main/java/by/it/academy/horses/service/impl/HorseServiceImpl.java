package by.it.academy.horses.service.impl;

import by.it.academy.horses.filter.HorseFilter;
import by.it.academy.horses.repository.Horse;
import by.it.academy.horses.repository.HorseRepository;
import by.it.academy.horses.repository.SpecificationHorse;
import by.it.academy.horses.service.HorseService;
import by.it.academy.horses.service.dto.HorseDto;
import by.it.academy.horses.service.mappers.HorseMapper;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HorseServiceImpl implements HorseService {

    private final HorseRepository horseRepository;
    private final HorseMapper horseMapper;

    public HorseServiceImpl(HorseRepository horseRepository, HorseMapper horseMapper) {
        this.horseRepository = horseRepository;
        this.horseMapper = horseMapper;
    }

    @Override
    public List<HorseDto> findAll() {
        return horseRepository.findAll().stream()
                .map(horseMapper::toHorseDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<HorseDto> findAllPaginated(HorseFilter horseFilter, int pageNumber, int pageSize, String sortField, String sortDirection) {
        Specification<Horse> horseSpecification =
                Specification
                        .where(Optional.ofNullable(horseFilter.getAgeFilter())
                                .map(SpecificationHorse::getHorseByAgeSpec)
                                .orElse(null))
                        .and(Optional.ofNullable(horseFilter.getPriceFilter())
                                .map(SpecificationHorse::getHorseByPriceSpec)
                                .orElse(null))
                        .and(Optional.ofNullable(horseFilter.getTypeFilter())
                                .map(SpecificationHorse::getHorseByTypeSpec)
                                .orElse(null));
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable paged = PageRequest.of(pageNumber - 1, pageSize, sort);
        return horseRepository.findAll(horseSpecification, paged).map(horseMapper::toHorseDto);
    }

    @Override
    public HorseDto findById(Long horseId) {
        Horse horse = horseRepository.findById(horseId).orElse(null);
        return horseMapper.toHorseDto(horse);
    }

    @Override
    public void save(HorseDto horseDto) {
        horseRepository.save(horseMapper.toHorse(horseDto));
    }

    @Override
    public void deleteById(Long horseId) {
        horseRepository.deleteById(horseId);
    }
}
