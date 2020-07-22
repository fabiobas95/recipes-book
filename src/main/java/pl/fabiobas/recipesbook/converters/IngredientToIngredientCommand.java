package pl.fabiobas.recipesbook.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import pl.fabiobas.recipesbook.commands.IngredientCommand;
import pl.fabiobas.recipesbook.model.Ingredient;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    private final UnitOfMeasureToUnitOfMeasureCommand uomConverter;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Nullable
    @Override
    public IngredientCommand convert(Ingredient source) {
        if (source == null) {
            return null;
        }

        final IngredientCommand command = new IngredientCommand();
        command.setId(source.getId());
        if (source.getRecipe() != null) {
            command.setRecipeId(source.getRecipe().getId());
        }
        command.setAmount(source.getAmount());
        command.setDescription(source.getDescription());
        command.setUom(uomConverter.convert(source.getUom()));
        return command;
    }
}
