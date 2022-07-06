package by.it.academy.horses.controller;

import by.it.academy.horses.filter.HorseFilter;
import by.it.academy.horses.service.HorseService;
import by.it.academy.horses.service.dto.HorseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class HorseController {

    private final HorseService horseService;

    public HorseController(HorseService horseService) {
        this.horseService = horseService;
    }

    @GetMapping("/horse-create")
    public String newHorse(@ModelAttribute("horse") HorseDto horseDto) {
        return "horse-create";
    }

    @PostMapping()
    public String createHorse(@ModelAttribute("horse") HorseDto horseDto) {
        horseService.save(horseDto);
        return "redirect:/";
    }

    @GetMapping("/{id}/{pageNumber}/{sortField}/{sortDir}")
    public String editHorse(Model model, @PathVariable("id") long id, @PathVariable int pageNumber, @PathVariable String sortField,
                            @PathVariable String sortDir) {
        HorseDto horseDto = horseService.findById(id);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("horse", horseDto);
        return "horse-update";
    }

    @PatchMapping("/{id}")
    public String updateHorse(Model model, @ModelAttribute("horse") @Valid HorseDto horseDto, int pageNumber, String sortField, String sortDir,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "horse-update";
        horseService.save(horseDto);
        return getPaginatedHorses(pageNumber, sortField, sortDir, null, null, null, model);
    }

    @DeleteMapping("/{id}/{pageNumber}/{sortField}/{sortDir}")
    public String deleteHorse(Model model, @PathVariable("id") long id, @PathVariable("pageNumber") int pageNumber, @PathVariable("sortField") String sortField,
                              @PathVariable("sortDir") String sortDir) {
        horseService.deleteById(id);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        return getPaginatedHorses(pageNumber, sortField, sortDir, null, null, null, model);
    }

    @GetMapping("/page/{pageNumber}")
    public String getPaginatedHorses(@PathVariable("pageNumber") int pageNumber, @RequestParam("sortField") String sortField,
                                     @RequestParam("sortDir") String sortDir, @Param("typeFilter") String typeFilter,
                                     @Param("ageFilter") Integer ageFilter, @Param("priceFilter") Double priceFilter, Model model) {
        HorseFilter horseFilter = HorseFilter.builder()
                .typeFilter(typeFilter)
                .ageFilter(ageFilter)
                .priceFilter(priceFilter)
                .build();
        Page<HorseDto> page = horseService.findAllPaginated(horseFilter, pageNumber, sortField, sortDir);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<HorseDto> horses = page.getContent();
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("horses", horses);
        return "horse-list";
    }

    @GetMapping("/")
    public String showHorsesFirstPage(Model model) {
        return getPaginatedHorses(1, "id", "asc", null, null, null, model);
    }
}