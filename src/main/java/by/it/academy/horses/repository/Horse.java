package by.it.academy.horses.repository;

import lombok.*;

import javax.persistence.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "horses")
public class Horse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "typ")
    private String type;

    @Column(name = "age")
    private Integer age;

    @Column(name = "price")
    private Double price;
}
