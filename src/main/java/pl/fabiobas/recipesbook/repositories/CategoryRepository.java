package pl.fabiobas.recipesbook.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.fabiobas.recipesbook.model.Category;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findByDescription(String description);
}
