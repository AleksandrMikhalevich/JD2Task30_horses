package by.it.academy.horses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface HorseRepository extends JpaRepository<Horse, Long>, JpaSpecificationExecutor<Horse> {

}
