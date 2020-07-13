package pl.fabiobas.recipesbook.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.fabiobas.recipesbook.model.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
