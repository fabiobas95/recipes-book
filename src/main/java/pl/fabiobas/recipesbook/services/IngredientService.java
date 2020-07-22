package pl.fabiobas.recipesbook.services;

import pl.fabiobas.recipesbook.commands.IngredientCommand;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
