package pl.fabiobas.recipesbook.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.fabiobas.recipesbook.model.Recipe;
import pl.fabiobas.recipesbook.repositories.RecipeRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipes() {
        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        return recipes;
    }

    @Override
    public Recipe findById(Long l) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(l);

        if (recipeOptional.isEmpty()) {
            throw new RuntimeException("Recipe not found!");
        }

        return recipeOptional.get();
    }

}
