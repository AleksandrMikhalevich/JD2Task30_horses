package by.it.academy.horses.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HorseFilter {
    private String typeFilter;
    private Integer ageFilter;
    private Double priceFilter;
}
