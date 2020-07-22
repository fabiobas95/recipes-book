package pl.fabiobas.recipesbook.services;

import org.springframework.stereotype.Service;
import pl.fabiobas.recipesbook.commands.IngredientCommand;
import pl.fabiobas.recipesbook.converters.IngredientToIngredientCommand;
import pl.fabiobas.recipesbook.model.Recipe;
import pl.fabiobas.recipesbook.repositories.RecipeRepository;

import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final RecipeRepository recipeRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;

    public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientToIngredientCommand ingredientToIngredientCommand) {
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
    }


    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        if (recipeOptional.isEmpty()) {
            //todo implement error handling
        }
        Recipe recipe = recipeOptional.get();
        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredientToIngredientCommand::convert).findFirst();

        if (ingredientCommandOptional.isEmpty()) {
            //todo implement error handling
        }
        return ingredientCommandOptional.get();
    }
}
