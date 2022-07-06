package by.it.academy.horses.repository;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class SpecificationHorse {

    public static Specification<Horse> getHorseByTypeSpec(String type) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicatesMain = new ArrayList<>();
            if (type != null) {
                predicatesMain.add(criteriaBuilder.equal(root.get(Horse_.TYPE), type));
            }
            return criteriaBuilder.and(predicatesMain.toArray(new Predicate[predicatesMain.size()]));
        };
    }
    public static Specification<Horse> getHorseByAgeSpec(Integer age) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicatesMain = new ArrayList<>();
            if (age != null) {
                predicatesMain.add(criteriaBuilder.like(root.get(Horse_.AGE).as(String.class), "%" + age + "%"));
            }
            return criteriaBuilder.and(predicatesMain.toArray(new Predicate[predicatesMain.size()]));
        };
    }

    public static Specification<Horse> getHorseByPriceSpec(Double price) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicatesMain = new ArrayList<>();
            if (price != null) {
                predicatesMain.add(criteriaBuilder.like(root.get(Horse_.PRICE).as(String.class), "%" + price + "%"));
            }
            return criteriaBuilder.and(predicatesMain.toArray(new Predicate[predicatesMain.size()]));
        };
    }

}
