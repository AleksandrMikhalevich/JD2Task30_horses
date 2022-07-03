package by.it.academy.horses.controller;

import by.it.academy.horses.service.HorseService;
import by.it.academy.horses.service.dto.HorseDto;
import org.springframework.data.domain.Page;
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

//    @GetMapping()
//    public String index(Model model) {
////        HorseFilter horseFilter = HorseFilter.builder()
////                //.ageFilter(5)
////                .priceFilter(1000.00)
////                .build();
////        model.addAttribute("horses", horseService.findAll(horseFilter));
//        model.addAttribute("horses", horseService.findAll());
//        return "horse-list";
//    }

    @GetMapping("/horse-create")
    public String newHorse(@ModelAttribute("horse") HorseDto horseDto) {
        return "horse-create";
    }

    @PostMapping()
    public String createHorse(@ModelAttribute("horse") HorseDto horseDto) {
        horseService.save(horseDto);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String editHorse(Model model, @PathVariable("id") long id) {
        HorseDto horseDto = horseService.findById(id);
        model.addAttribute("horse", horseDto);
        return "horse-update";
    }

    @PatchMapping("/{id}")
    public String updateHorse(@ModelAttribute("horse") @Valid HorseDto horseDto,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "horse-update";
        horseService.save(horseDto);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String deleteHorse(@PathVariable("id") long id) {
        horseService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/page/{pageNumber}")
    public String getPaginatedHorses(@PathVariable(value = "pageNumber") int pageNumber, @RequestParam("sortField") String sortField,
                                     @RequestParam("sortDir") String sortDir,Model model) {
        Page<HorseDto> page = horseService.findAllPaginated(pageNumber, sortField, sortDir);
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
    public String showHorsesFirstPage(Model model){
        return getPaginatedHorses(1, "id", "asc", model);
    }
}