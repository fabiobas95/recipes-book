package pl.fabiobas.recipesbook.services;

import pl.fabiobas.recipesbook.commands.RecipeCommand;
import pl.fabiobas.recipesbook.model.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long l);

    RecipeCommand findCommandById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    void deleteById(Long id);
}
