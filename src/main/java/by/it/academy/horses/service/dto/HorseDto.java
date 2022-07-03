package by.it.academy.horses.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HorseDto implements Serializable {
    private Long id;
    @NotEmpty(message = "Type should not be empty")
    @Size(min = 2, max = 30, message = "Type should be between 2 and 30 characters")
    private String type;
    @Min(value = 2, message = "Age must be between 2 and 30")
    @Max(value = 30, message = "Age must be between 2 and 30")
    private Integer age;
    @Min(value = 1, message = "Price should be greater than 0")
    private Double price;
}
