package by.it.academy.horses.repository;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class SpecificationHorse {
    public static Specification<Horse> getHorseByAgeSpec(Integer age) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicatesMain = new ArrayList<>();
            if (age != null) {
                predicatesMain.add(criteriaBuilder.equal(root.get(Horse_.AGE), age));
            }
            return criteriaBuilder.and(predicatesMain.toArray(new Predicate[predicatesMain.size()]));
        };
    }

    public static Specification<Horse> getHorseByPriceSpec(Double price) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicatesMain = new ArrayList<>();
            if (price != null) {
                predicatesMain.add(criteriaBuilder.equal(root.get(Horse_.PRICE), price));
            }
            return criteriaBuilder.and(predicatesMain.toArray(new Predicate[predicatesMain.size()]));
        };
    }

}
