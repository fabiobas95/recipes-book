package pl.fabiobas.recipesbook.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.fabiobas.recipesbook.model.UnitOfMeasure;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

    Optional<UnitOfMeasure> findByDescription(String description);
}
