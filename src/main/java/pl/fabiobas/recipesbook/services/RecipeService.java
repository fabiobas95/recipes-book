package pl.fabiobas.recipesbook.services;

import pl.fabiobas.recipesbook.model.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();
}
